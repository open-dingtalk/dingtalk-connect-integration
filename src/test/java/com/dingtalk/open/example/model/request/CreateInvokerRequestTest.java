package com.dingtalk.open.example.model.request;

import com.alibaba.fastjson.JSON;
import com.dingtalk.open.example.TestApplicationConfiguration;
import com.dingtalk.open.example.exception.DingException;
import com.dingtalk.open.example.model.response.DingtalkActionVO;
import com.dingtalk.open.example.model.response.InvokeInstanceResponse;
import com.dingtalk.open.example.model.response.QueryActionResponse;
import com.dingtalk.open.example.model.response.UpdateInvokerResponse;
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
public class CreateInvokerRequestTest {
    @Autowired
    private DingtalkOpenClient dingtalkOpenClient;

    @Test
    public void test() throws DingException {
        QueryActionsRequest queryActionsRequest = new QueryActionsRequest("G-CONN-1015BC8093540B01B8D0000Q", "ding32fff839a3e0105d");
        QueryActionResponse queryActionResponse = dingtalkOpenClient.invokeOpenApi(queryActionsRequest);
        for (DingtalkActionVO dingtalkActionVO : queryActionResponse.getList()) {
            // 查询详情
            String instanceKey = "ding32fff839a3e0105d";
            CreateInvokerRequest createInvokerRequest = new CreateInvokerRequest(dingtalkActionVO.getConnectAssetUri(), instanceKey);
            //noinspection TestFailedLine
            UpdateInvokerResponse updateInvokerResponse = dingtalkOpenClient.invokeOpenApi(createInvokerRequest);
            log.info(JSON.toJSONString(updateInvokerResponse));
            InvokeInstanceRequest invokeInstanceRequest = new InvokeInstanceRequest(dingtalkActionVO.getConnectAssetUri(), instanceKey, "{}");
            InvokeInstanceResponse invokeInstanceResponse = dingtalkOpenClient.invokeOpenApi(invokeInstanceRequest);
            log.info(JSON.toJSONString(invokeInstanceResponse));
            log.info("=======");
            break;
        }
    }

}
