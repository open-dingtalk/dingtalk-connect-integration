package com.dingtalk.open.example.model;

import com.dingtalk.open.example.exception.DingException;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 说明：
 *
 * @author donghuai.zjj
 * @date 2022/12/14
 */
public abstract class DingRequest<T extends DingResponse> {

    public static final String CODE_OK = "0";

    protected abstract RequestEntity<?> toEntity();

    public final T callHttp(RestTemplate restTemplate) throws DingException {
        Type type = getGenericType();
        ResponseEntity<Object> result = restTemplate.exchange(toEntity(), ParameterizedTypeReference.forType(type));
        // 接口异常判断
        if (result.getStatusCode().isError()) {
            throw new DingException("oapi.status.error", "status:" + result.getStatusCodeValue());
        }
        //noinspection unchecked
        T bodyPojo = (T) result.getBody();
        if (bodyPojo == null) {
            throw new DingException("oapi.result.empty","empty result");
        }
        if (!bodyPojo.getErrorCode().equals(CODE_OK)) {
            throw new DingException(bodyPojo.getErrorCode(), bodyPojo.getErrorMessage());
        }
        return bodyPojo;
    }

    private Type getGenericType() {
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        return ArrayUtils.get(genericSuperclass.getActualTypeArguments(), 0);
    }
}
