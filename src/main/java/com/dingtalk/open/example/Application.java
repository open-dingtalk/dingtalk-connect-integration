package com.dingtalk.open.example;

import com.dingtalk.open.example.configuration.DingtalkConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 说明：
 *
 * @author donghuai.zjj
 * @date 2022/12/14
 */
@SpringBootApplication(
        scanBasePackages = {
                "com.dingtalk.open.example"
        }
)
@EnableConfigurationProperties(
        DingtalkConfiguration.class
)
@EnableScheduling
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}