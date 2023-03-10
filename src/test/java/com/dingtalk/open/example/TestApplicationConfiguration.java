package com.dingtalk.open.example;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.dingtalk.open.example.configuration.DingtalkConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 说明：
 *
 * @author donghuai.zjj
 * @date 2022/12/16
 */
@EnableConfigurationProperties(
        DingtalkConfiguration.class
)
@EnableScheduling
@ComponentScan("com.dingtalk.open.example")
public class TestApplicationConfiguration {
}
