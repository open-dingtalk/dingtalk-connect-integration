package com.dingtalk.open.example.model.annotation;


import org.springframework.http.HttpMethod;

import java.lang.annotation.*;

/**
 * 说明：
 *
 * @author donghuai.zjj
 * @date 2022/12/14
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DingtalkOpenApi {
    String value();

    HttpMethod method() default HttpMethod.POST;
}
