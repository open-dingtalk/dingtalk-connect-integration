package com.dingtalk.open.example.model.response;

import lombok.Data;

import java.util.List;

/**
 * 说明：
 *
 * @author donghuai.zjj
 * @date 2022/12/27
 */
@Data
public class IntegrationConfigVO {
    private String entityName;
    private List<CategoryNameVO> categoryNames;
    private List<PropVO> props;

    @Data
    public static class CategoryNameVO {
        String value;
    }

    @Data
    public static class PropVO {
        String key;
        String value;
    }
}
