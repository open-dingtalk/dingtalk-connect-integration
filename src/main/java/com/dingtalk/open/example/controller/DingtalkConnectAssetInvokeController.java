package com.dingtalk.open.example.controller;

import com.dingtalk.open.example.exception.DingException;
import com.dingtalk.open.example.model.request.CreateInvokerRequest;
import com.dingtalk.open.example.model.request.InvokeInstanceRequest;
import com.dingtalk.open.example.model.response.InvokeInstanceResponse;
import com.dingtalk.open.example.model.response.UpdateInvokerResponse;
import com.dingtalk.open.example.support.client.DingtalkOpenClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 说明：
 *
 * @author donghuai.zjj
 * @date 2023/01/04
 */
@RestController
@RequestMapping("/v1/dingtalk/asset/invoker")
public class DingtalkConnectAssetInvokeController {
    private final DingtalkOpenClient dingtalkOpenClient;

    @Autowired
    public DingtalkConnectAssetInvokeController(DingtalkOpenClient dingtalkOpenClient) {
        this.dingtalkOpenClient = dingtalkOpenClient;
    }

    @PostMapping
    public UpdateInvokerResponse createInvoker(@RequestParam String connectAssetUri) throws DingException {
        return dingtalkOpenClient.invokeOpenApi(new CreateInvokerRequest(connectAssetUri, "test"));
    }

    @PostMapping("/_invoke")
    public InvokeInstanceResponse invoker(@RequestParam String connectAssetUri,
                                          @RequestBody String input) throws DingException {
        return dingtalkOpenClient.invokeOpenApi(new InvokeInstanceRequest(connectAssetUri, "test", input));
    }
}
