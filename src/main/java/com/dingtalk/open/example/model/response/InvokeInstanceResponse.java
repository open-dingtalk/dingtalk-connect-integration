package com.dingtalk.open.example.model.response;

import com.dingtalk.open.example.model.DingResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 说明：
 *
 * @author donghuai.zjj
 * @date 2022/12/29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InvokeInstanceResponse extends DingResponse {
    private String outputJson;

    private String instanceId;

    private Long cost;
}
