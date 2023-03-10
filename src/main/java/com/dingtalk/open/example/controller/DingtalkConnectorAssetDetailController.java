package com.dingtalk.open.example.controller;

import com.dingtalk.open.example.exception.DingException;
import com.dingtalk.open.example.model.request.GetActionDetailRequest;
import com.dingtalk.open.example.model.response.GetActionDetailResponse;
import com.dingtalk.open.example.support.client.DingtalkOpenClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 说明：
 *
 * @author donghuai.zjj
 * @date 2023/01/03
 */
@RestController
@RequestMapping("/v1/dingtalk/asset/details")
public class DingtalkConnectorAssetDetailController {
    private final DingtalkOpenClient dingtalkOpenClient;

    @Autowired
    public DingtalkConnectorAssetDetailController(DingtalkOpenClient dingtalkOpenClient) {
        this.dingtalkOpenClient = dingtalkOpenClient;
    }

    @GetMapping("_query-detail")
    public GetActionDetailResponse detail(@RequestParam String connectAssetUri) throws DingException {
        return dingtalkOpenClient.invokeOpenApi(new GetActionDetailRequest(connectAssetUri));
    }
}
