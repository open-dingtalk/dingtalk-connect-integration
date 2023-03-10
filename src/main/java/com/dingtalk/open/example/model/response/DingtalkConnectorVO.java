package com.dingtalk.open.example.model.response;

import com.alibaba.fastjson2.JSON;
import lombok.Data;

/**
 * 说明：
 *
 * @author donghuai.zjj
 * @date 2022/12/15
 */
@Data
public class DingtalkConnectorVO {
    String id;
    String name;
    String description;
    String providerCorpId;
    String icon;

}
