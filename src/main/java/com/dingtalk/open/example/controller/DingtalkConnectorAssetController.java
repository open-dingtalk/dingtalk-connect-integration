package com.dingtalk.open.example.controller;

import com.dingtalk.open.example.exception.DingException;
import com.dingtalk.open.example.model.IterableResult;
import com.dingtalk.open.example.model.request.QueryActionsRequest;
import com.dingtalk.open.example.model.response.DingtalkActionVO;
import com.dingtalk.open.example.model.response.DingtalkConnectorVO;
import com.dingtalk.open.example.model.response.QueryActionResponse;
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
 * @date 2023/01/03
 */
@RestController
@RequestMapping("/v1/dingtalk/asset")
public class DingtalkConnectorAssetController {
    private final DingtalkOpenClient dingtalkOpenClient;

    @Autowired
    public DingtalkConnectorAssetController(DingtalkOpenClient dingtalkOpenClient) {
        this.dingtalkOpenClient = dingtalkOpenClient;
    }

    @GetMapping("/actions")
    public IterableResult<DingtalkActionVO> actions(
            @RequestParam(defaultValue = "0") Long cursor,
            @RequestParam String connectorId,
            @RequestParam String providerCorpId
    ) throws DingException {
        QueryActionResponse response = dingtalkOpenClient.invokeOpenApi(new QueryActionsRequest(connectorId, providerCorpId));
        List<DingtalkActionVO> actions = ListUtils.emptyIfNull(response.getList());
        return IterableResult.build(actions, String.valueOf(response.getNextCursor()), response.getNextCursor() != null);
    }


}
