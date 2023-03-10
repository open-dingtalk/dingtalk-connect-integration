package com.dingtalk.open.example.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.oapi.lib.aes.DingTalkEncryptException;
import com.dingtalk.oapi.lib.aes.DingTalkEncryptor;
import com.dingtalk.open.example.configuration.DingtalkConfiguration;
import com.dingtalk.open.example.support.client.DingtalkOpenClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

/**
 * 说明：
 *
 * @author donghuai.zjj
 * @date 2023/02/01
 */
@RestController
@RequestMapping("/v1/dingtalk/callback")
@Slf4j
public class DingtalkCallbackController {

    /**
     * 创建应用，验证回调URL创建有效事件（第一次保存回调URL之前）
     */
    private static final String EVENT_CHECK_CREATE_SUITE_URL = "check_create_suite_url";

    /**
     * 创建应用，验证回调URL变更有效事件（第一次保存回调URL之后）
     */
    private static final String EVENT_CHECK_UPDATE_SUITE_URL = "check_update_suite_url";

    /**
     * suite_ticket推送事件
     */
    private static final String EVENT_SUITE_TICKET = "suite_ticket";

    /**
     * 企业授权开通应用事件
     */
    private static final String EVENT_TMP_AUTH_CODE = "tmp_auth_code";
    public static final String SYNC_HTTP_PUSH_HIGH = "SYNC_HTTP_PUSH_HIGH";
    private final DingtalkConfiguration dingtalkConfiguration;

    @Autowired
    public DingtalkCallbackController(DingtalkConfiguration dingtalkConfiguration) {
        this.dingtalkConfiguration = dingtalkConfiguration;
    }

    @PostMapping
    public Map<String, String> receive(
            @RequestParam(value = "signature") String signature,
            @RequestParam(value = "timestamp") Long timestamp,
            @RequestParam(value = "nonce") String nonce,
            @RequestBody(required = false) JSONObject body) {
        try {
            DingTalkEncryptor dingTalkEncryptor = new DingTalkEncryptor(dingtalkConfiguration.getEncryptToken(),
                    dingtalkConfiguration.getAesKey(), dingtalkConfiguration.getSuiteKey());
            // 从post请求的body中获取回调信息的加密数据进行解密处理
            String encrypt = body.getString("encrypt");
            String plainText = dingTalkEncryptor.getDecryptMsg(signature, timestamp.toString(), nonce, encrypt);
            JSONObject callBackContent = JSON.parseObject(plainText);
            // 根据回调事件类型做不同的业务处理
            String eventType = callBackContent.getString("EventType");
            if (EVENT_CHECK_CREATE_SUITE_URL.equals(eventType)) {
                log.info("验证新创建的回调URL有效性: " + plainText);
            } else if (EVENT_CHECK_UPDATE_SUITE_URL.equals(eventType)) {
                log.info("验证更新回调URL有效性: " + plainText);
            } else if (EVENT_SUITE_TICKET.equals(eventType)) {
                // suite_ticket用于用签名形式生成accessToken(访问钉钉服务端的凭证)，需要保存到应用的db。
                // 钉钉会定期向本callback url推送suite_ticket新值用以提升安全性。
                // 应用在获取到新的时值时，保存db成功后，返回给钉钉success加密串（如本demo的return）
                log.info("应用suite_ticket数据推送: " + plainText);
            } else if (EVENT_TMP_AUTH_CODE.equals(eventType)) {
                // 本事件应用应该异步进行授权开通企业的初始化，目的是尽最大努力快速返回给钉钉服务端。用以提升企业管理员开通应用体验
                // 即使本接口没有收到数据或者收到事件后处理初始化失败都可以后续再用户试用应用时从前端获取到corpId并拉取授权企业信息，进而初始化开通及企业。
                log.info("企业授权开通应用事件: " + plainText);
            } else if (SYNC_HTTP_PUSH_HIGH.equals(eventType)){
                JSONArray events = callBackContent.getJSONArray("bizData");
                for (int i = 0; i < events.size(); i++) {
                    JSONObject event = events.getJSONObject(i);
                    int bizType = event.getIntValue("biz_type");
                    if (bizType == 2) {
                        String bizData = event.getString("biz_data");
                        JSONObject data = JSONObject.parseObject(bizData);
                        String suiteTicket = data.getString("suiteTicket");
                        DingtalkOpenClient.IsvCorpToken.setSuiteTicket(suiteTicket);
                    }
                    log.info("event={}", event.toJSONString());
                }
            }
            // 其他类型事件处理
            // 返回success的加密信息表示回调处理成功
            return dingTalkEncryptor.getEncryptedMap("success", timestamp, nonce);
        } catch (DingTalkEncryptException e) {
            log.error("{}, {}, {}", signature, timestamp, nonce, e);
            return Collections.singletonMap("success", "false");
        }

    }
}
