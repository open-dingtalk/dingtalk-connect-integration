package com.dingtalk.open.example.model.request;

import com.alibaba.fastjson.JSON;
import com.dingtalk.open.example.TestApplicationConfiguration;
import com.dingtalk.open.example.exception.DingException;
import com.dingtalk.open.example.model.response.QueryActionResponse;
import com.dingtalk.open.example.support.client.DingtalkOpenClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 说明：
 *
 * @author donghuai.zjj
 * @date 2022/12/26
 *
 */

@SpringBootTest(classes = TestApplicationConfiguration.class)
public class QueryActionsRequestTest {
    @Autowired
    private DingtalkOpenClient dingtalkOpenClient;

    @Test
    public void test() throws DingException {
        QueryActionsRequest request = new QueryActionsRequest("G-CONN-1015BC8093540B01B8D0000Q", "ding32fff839a3e0105d");
        QueryActionResponse response = dingtalkOpenClient.invokeOpenApi(request);
        System.out.println(JSON.toJSONString(response));
    }

}
