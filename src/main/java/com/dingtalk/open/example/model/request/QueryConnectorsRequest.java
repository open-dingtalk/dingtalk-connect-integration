package com.dingtalk.open.example.model.request;

import com.dingtalk.open.example.model.DingRequest;
import com.dingtalk.open.example.model.response.QueryConnectorsResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.RequestEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

/**
 * 说明：查询连接器
 *
 * @author donghuai.zjj
 * @date 2022/12/14
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QueryConnectorsRequest extends DingRequest<QueryConnectorsResponse> {
    private final String type;
    private final long nextToken;
    private final long size;

    public QueryConnectorsRequest(String type, long nextToken, long size) {
        this.type = type;
        this.nextToken = nextToken;
        this.size = size;
    }

    public QueryConnectorsRequest(String type, long nextToken) {
        this(type, nextToken, 50);
    }

    public QueryConnectorsRequest(String type) {
        this(type, 0L);
    }

    @Override
    protected RequestEntity<?> toEntity() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath("/v1.0/connector/assets/connectors")
                .queryParamIfPresent("type", Optional.ofNullable(type))
                .queryParamIfPresent("nextToken", Optional.of(nextToken))
                .queryParamIfPresent("maxResults", Optional.of(size));
        return RequestEntity.get(uriBuilder.build().toUriString()).build();
    }
}
