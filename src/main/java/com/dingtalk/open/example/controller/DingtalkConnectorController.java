package com.dingtalk.open.example.controller;

import com.dingtalk.open.example.exception.DingException;
import com.dingtalk.open.example.model.IterableResult;
import com.dingtalk.open.example.model.request.QueryConnectorsRequest;
import com.dingtalk.open.example.model.response.DingtalkConnectorVO;
import com.dingtalk.open.example.model.response.QueryConnectorsResponse;
import com.dingtalk.open.example.support.client.DingtalkOpenClient;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 说明：
 *
 * @author donghuai.zjj
 * @date 2022/12/15
 */
@RestController
@RequestMapping("/v1/dingtalk/connector")
public class DingtalkConnectorController {

    private final DingtalkOpenClient dingtalkOpenClient;

    @Autowired
    public DingtalkConnectorController(DingtalkOpenClient dingtalkOpenClient) {
        this.dingtalkOpenClient = dingtalkOpenClient;
    }

    @GetMapping
    public IterableResult<DingtalkConnectorVO> searchWithIterableResult(
            @RequestParam(defaultValue = "official") String type,
            @RequestParam(defaultValue = "0") Long cursor) throws DingException {
        QueryConnectorsResponse response = dingtalkOpenClient.invokeOpenApi(new QueryConnectorsRequest(type, cursor));
        List<DingtalkConnectorVO> connectors = ListUtils.emptyIfNull(response.getList());
        return IterableResult.build(connectors, String.valueOf(response.getNextCursor()), response.getHasMore());
    }
}
