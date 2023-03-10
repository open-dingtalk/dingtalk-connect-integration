package com.dingtalk.open.example.model;

import com.dingtalk.open.example.model.response.DingtalkConnectorVO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * 说明：可遍历结果
 *
 * @author donghuai.zjj
 * @date 2022/12/15
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IterableResult<T> {
    private List<T> list;

    private String next;

    private boolean hasMore;

    public static <T> IterableResult<T> empty() {
        IterableResult<T> iterableResult = new IterableResult<>();
        iterableResult.setList(Collections.emptyList());
        iterableResult.setNext(null);
        iterableResult.setHasMore(false);
        return iterableResult;
    }

    public static <T> IterableResult<T> build(List<T> list, String nextCursor, Boolean hasMore) {
        IterableResult<T> result = new IterableResult<>();
        result.setList(list);
        result.setNext(nextCursor);
        result.setHasMore(Boolean.TRUE.equals(hasMore));
        return result;
    }
}
