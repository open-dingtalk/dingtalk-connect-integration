package com.dingtalk.open.example.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 说明：钉钉响应结果
 *
 * @author donghuai.zjj
 * @date 2022/12/14
 */
@Data
public class DingResponse {
    @JsonProperty("errcode")
    @JSONField(name = "errcode")
    private String errorCode = "0";
    @JsonProperty("errmsg")
    @JSONField(name = "errmsg")
    private String errorMessage;
}
