package com.dingtalk.open.example.model.response;

import com.dingtalk.open.example.model.DingResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 说明：
 *
 * @author donghuai.zjj
 * @date 2022/12/27
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GetActionDetailResponse extends DingResponse {
    /**
     * 入参
     */
    private String inputSchema;
    /**
     * 出参
     */
    private String outputSchema;
    /**
     * 资产地址
     */
    private String connectAssetUri;
    /**
     * 名称
     */
    private String name;
    /**
     * 集成配置
     */
    private IntegrationConfigVO integrationConfig;
    /**
     * 资产ID
     */
    private String refId;
    /**
     * 资产类型
     */
    private String refType;
    /**
     * 提供方ID
     */
    private String refProviderCorpId;
}
