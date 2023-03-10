package com.dingtalk.open.example.support.client;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson2.JSONObject;
import com.dingtalk.open.example.configuration.DingtalkConfiguration;
import com.dingtalk.open.example.exception.DingException;
import com.dingtalk.open.example.model.DingRequest;
import com.dingtalk.open.example.model.DingResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 说明：
 *
 * @author donghuai.zjj
 * @date 2022/12/14
 */
@Component
@Slf4j
public class DingtalkOpenClient implements InitializingBean {
    public static final String SUCCESS_CODE = "0";
    private final RestTemplate restTemplate;
    private final DingtalkConfiguration dingtalkConfiguration;
    private final AtomicReference<Token> tokenRef = new AtomicReference<>();

    public DingtalkOpenClient(DingtalkConfiguration dingtalkConfiguration) {
        this.dingtalkConfiguration = dingtalkConfiguration;
        this.restTemplate = new RestTemplateBuilder()
                .additionalInterceptors((request, body, execution) -> {
                    log.info("{}:{}", request.getMethod(), request.getURI());
                    request.getHeaders().set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
                    try {
                        if (StringUtils.startsWithAny(request.getURI().getPath(), "/gettoken", "/v1.0/oauth2/corpAccessToken")) {
                            return execution.execute(request, body);
                        }
                        request.getHeaders().set("x-acs-dingtalk-access-token", getToken());
                    } catch (DingException e) {
                        throw new IOException(e.getMessage(), e);
                    }
                    ClientHttpResponse result = execution.execute(request, body);
                    String requestId = ListUtils.emptyIfNull(result.getHeaders().get("x-acs-request-id"))
                            .stream().findFirst().orElse(null);
                    log.info("request={}, status={}, requestId={}",
                            request.getURI().getPath(), result.getStatusCode().value(), requestId);
                    return result;
                })
                .setConnectTimeout(Duration.ofSeconds(30))
                .setReadTimeout(Duration.ofSeconds(5))
                .rootUri(dingtalkConfiguration.getEndpoint())
                .build();
    }

    private interface Token {

        String getValue();

        boolean checkExpire();
    }

    private static class CorpToken implements Token {

        @Getter(onMethod_ = {@Override})
        String value;
        Long expireTime;

        @Override
        public boolean checkExpire() {
            return System.currentTimeMillis() >= expireTime;
        }
    }

    public static class IsvCorpToken implements Token {
        private static final ThreadLocal<String> currentCorp = new ThreadLocal<>();
        private static final Map<String, CorpToken> multiCorpToken = new ConcurrentHashMap<>();
        private static final IsvCorpToken instance = new IsvCorpToken();

        @Getter
        private String suiteTicket;

        public static void putToken(String corpId, CorpToken token) {
            multiCorpToken.put(corpId, token);
        }

        public static void setSuiteTicket(String suiteTicket) {
            instance.suiteTicket = suiteTicket;
        }

        public static String currentCorp() {
            return currentCorp.get();
        }

        public static void setCurrentCorp(String corpId) {
            currentCorp.set(corpId);
        }

        public static void clear() {
            currentCorp.remove();
        }

        @Override
        public String getValue() {
            String corpId = currentCorp.get();
            return Optional.ofNullable(corpId)
                    .map(multiCorpToken::get)
                    .filter(corpToken -> !corpToken.checkExpire())
                    .map(CorpToken::getValue)
                    .orElse(null);
        }

        @Override
        public boolean checkExpire() {
            String corpId = currentCorp.get();
            return Optional.ofNullable(corpId)
                    .map(multiCorpToken::get)
                    .map(corpToken -> !corpToken.checkExpire())
                    .orElse(true);
        }
    }

    /**
     * 调用开放接口
     *
     * @param request 请求
     * @param <T>     返回类型
     * @return 返回数据
     * @throws DingException 异常
     */
    public <T extends DingResponse> T invokeOpenApi(DingRequest<T> request) throws DingException {
        return request.callHttp(restTemplate);
    }

    private String getToken() throws DingException {
        return Optional.ofNullable(tokenRef.get())
                .map((Token token) -> {
                    if (token.checkExpire()) {
                        try {
                            refreshToken();
                        } catch (DingException e) {
                            log.warn("token refresh failed", e);
                            return null;
                        }
                    }
                    return token.getValue();
                })
                .orElseThrow(() -> new DingException("invalid.toke", "令牌已失效"));
    }

    /**
     * 每小时刷新1次
     *
     * @throws DingException 请求异常
     */
    @Scheduled(fixedDelay = 3600, timeUnit = TimeUnit.SECONDS, initialDelay = 3600)
    public void scheduledRefresh() throws DingException {
        refreshToken();
        log.info("[dingtalk] refresh token");
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class TokenResponse extends DingResponse {
        @JsonProperty("access_token")
        @JSONField(name = "accessToken")
        private String accessToken;
        @JsonProperty("accessToken")
        @JSONField(name = "suiteAccessToken")
        private String suiteAccessToken;
    }

    public void refreshToken() throws DingException {
        if (ObjectUtils.allNotNull(dingtalkConfiguration.getAppKey(), dingtalkConfiguration.getAppSecret())) {
            TokenResponse result = restTemplate.getForObject("/gettoken?appkey={appKey}&appsecret={appSecret}", TokenResponse.class, new JSONObject()
                    .fluentPut("appSecret", dingtalkConfiguration.getAppSecret())
                    .fluentPut("appKey", dingtalkConfiguration.getAppKey()));
            if (!SUCCESS_CODE.equals(Objects.requireNonNull(result).getErrorCode())) {
                throw new DingException(result.getErrorCode(), result.getErrorMessage());
            }
            String accessToken = result.getAccessToken();
            CorpToken token = new CorpToken();
            token.value = accessToken;
            token.expireTime = System.currentTimeMillis() + 3600 * 1000;
            tokenRef.set(token);
            return;
        }
        if (ObjectUtils.allNotNull(dingtalkConfiguration.getSuiteKey(), dingtalkConfiguration.getSuiteSecret())) {
            tokenRef.set(IsvCorpToken.instance);
            IsvCorpToken.setSuiteTicket(dingtalkConfiguration.getSuiteTicket());
            if (IsvCorpToken.currentCorp() == null) {
                return;
            }
            if (IsvCorpToken.instance.getSuiteTicket() == null) {
                return;
            }
            long timestamp = System.currentTimeMillis();
            TokenResponse result;
            JSONObject body = new JSONObject()
                    .fluentPut("suiteKey", dingtalkConfiguration.getSuiteKey())
                    .fluentPut("suiteSecret", dingtalkConfiguration.getSuiteSecret())
                    .fluentPut("suiteTicket", IsvCorpToken.instance.getSuiteTicket())
                    .fluentPut("authCorpId", IsvCorpToken.currentCorp());
            log.info("refresh token params={}", body.toJSONString());
            result = restTemplate.postForObject("/v1.0/oauth2/corpAccessToken",
                    body, TokenResponse.class);
            if (!SUCCESS_CODE.equals(Objects.requireNonNull(result).getErrorCode())) {
                throw new DingException(result.getErrorCode(), result.getErrorMessage());
            }
            String accessToken = result.getSuiteAccessToken();
            CorpToken token = new CorpToken();
            token.value = accessToken;
            token.expireTime = timestamp + 3600 * 1000;
            IsvCorpToken.putToken(IsvCorpToken.currentCorp(), token);
            return;
        }
        throw new DingException("invalid.configuration", "配置错误，请检查");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        refreshToken();
        log.info("[dingtalk] init token");
    }
}
