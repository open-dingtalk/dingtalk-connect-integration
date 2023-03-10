package com.dingtalk.open.example.model.response;

import lombok.Data;

/**
 * 说明：
 *
 * @author donghuai.zjj
 * @date 2022/12/27
 */
@Data
public class DingtalkActionVO {
    /**
     * 资产ID, 一般指action或trigger的ID
     */
    private String id;
    /**
     * 连接器的ID
     */
    private String connectorId;
    /**
     * 名称
     */
    private String name;
    /**
     * 说明
     */
    private String description;
    /**
     * 图标
     */
    private String icon;
    /**
     * 提供组织
     */
    private String providerCorpId;
    /**
     * 集成类型
     */
    private String integrationType;
    /**
     * 资产资源标识
     */
    private String connectAssetUri;
    /**
     * 授权状态
     */
    private boolean authorized;
    /**
     * 授权页地址
     */
    private String authorityUrl;
}
