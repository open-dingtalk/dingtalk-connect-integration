package com.dingtalk.open.example.model.response;

import com.dingtalk.open.example.model.DingResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 说明：
 *
 * @author donghuai.zjj
 * @date 2022/12/14
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QueryConnectorsResponse extends DingResponse {
    private List<DingtalkConnectorVO> list;
    private Boolean hasMore;
    private Long nextCursor;
    private Long totalCount;
    private String nextToken;
}
