package com.dingtalk.open.example.exception;

import lombok.Getter;

/**
 * 说明：
 *
 * @author donghuai.zjj
 * @date 2022/12/14
 */
public class DingException extends Exception {
    @Getter
    private final String code;
    @Getter
    private final String detail;

    public DingException(String code, String detail) {
        this.code = code;
        this.detail = detail;
    }

    @Override
    public String getMessage() {
        return code + ":" + detail;
    }
}
