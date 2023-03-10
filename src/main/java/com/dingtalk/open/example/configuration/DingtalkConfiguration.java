package com.dingtalk.open.example.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 说明：
 *
 * @author donghuai.zjj
 * @date 2022/12/14
 */
@ConfigurationProperties(
        prefix = "dingtalk"
)
@Data
public class DingtalkConfiguration {
    private String appKey;
    private String appSecret;

    private String encryptToken;

    private String aesKey;
    private String suiteKey;
    private String suiteSecret;
    private String endpoint = "https://oapi.dingtalk.com";

    private String suiteTicket;
}
