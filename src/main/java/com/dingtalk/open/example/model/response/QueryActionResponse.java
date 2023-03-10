package com.dingtalk.open.example.model.response;

import com.dingtalk.open.example.model.DingResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 说明：
 *
 * @author donghuai.zjj
 * @date 2022/12/26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class QueryActionResponse extends DingResponse {
    private List<DingtalkActionVO> list;
    private boolean hasMore;
    private Long nextCursor;
    private Long totalCount;
}
