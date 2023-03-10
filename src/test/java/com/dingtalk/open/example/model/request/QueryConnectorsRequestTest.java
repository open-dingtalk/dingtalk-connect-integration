package com.dingtalk.open.example.model.request;

import com.alibaba.fastjson.JSON;
import com.dingtalk.open.example.TestApplicationConfiguration;
import com.dingtalk.open.example.exception.DingException;
import com.dingtalk.open.example.model.response.QueryConnectorsResponse;
import com.dingtalk.open.example.support.client.DingtalkOpenClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;


/**
 * 说明：
 *
 * @author donghuai.zjj
 * @date 2022/12/16
 */
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class QueryConnectorsRequestTest {
    @Autowired
    private DingtalkOpenClient dingtalkOpenClient;

    @Test
    public void thirdparty() throws DingException {
        QueryConnectorsRequest request = new QueryConnectorsRequest("third_party", 0);
        QueryConnectorsResponse response = dingtalkOpenClient.invokeOpenApi(request);
        assertFalse(response.getList() == null || response.getList().isEmpty());
    }

    @Test
    public void official() throws DingException {
        QueryConnectorsRequest request = new QueryConnectorsRequest("official", 0);
        QueryConnectorsResponse response = dingtalkOpenClient.invokeOpenApi(request);
        System.out.println(JSON.toJSONString(response, true));
        assertFalse(response.getList() != null && response.getList().isEmpty());
    }

    @Test
    public void seflbuilt() throws DingException {
        QueryConnectorsRequest request = new QueryConnectorsRequest("self_built", 0);
        QueryConnectorsResponse response = dingtalkOpenClient.invokeOpenApi(request);
        assertFalse(response.getList() != null && response.getList().isEmpty());
    }
}