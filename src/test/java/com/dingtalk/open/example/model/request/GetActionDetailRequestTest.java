package com.dingtalk.open.example.model.request;

import com.alibaba.fastjson.JSON;
import com.dingtalk.open.example.TestApplicationConfiguration;
import com.dingtalk.open.example.exception.DingException;
import com.dingtalk.open.example.model.response.DingtalkActionVO;
import com.dingtalk.open.example.model.response.GetActionDetailResponse;
import com.dingtalk.open.example.model.response.QueryActionResponse;
import com.dingtalk.open.example.support.client.DingtalkOpenClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 说明：
 *
 * @author donghuai.zjj
 * @date 2022/12/27
 */
@SpringBootTest(classes = TestApplicationConfiguration.class)
@Slf4j
public class GetActionDetailRequestTest {
    @Autowired
    private DingtalkOpenClient dingtalkOpenClient;

    @Test
    public void test() throws DingException {
        QueryActionsRequest queryActionsRequest = new QueryActionsRequest("G-CONN-1018AB431018212B4AF70007", "ding32fff839a3e0105d");
        QueryActionResponse queryActionResponse = dingtalkOpenClient.invokeOpenApi(queryActionsRequest);
        for (DingtalkActionVO dingtalkActionVO : queryActionResponse.getList()) {
            GetActionDetailRequest getActionDetailRequest = new GetActionDetailRequest(dingtalkActionVO.getConnectAssetUri());
            GetActionDetailResponse getActionDetailResponse = dingtalkOpenClient.invokeOpenApi(getActionDetailRequest);
            log.info(JSON.toJSONString(getActionDetailResponse));
            log.info("=======");
            break;
        }
    }
}
