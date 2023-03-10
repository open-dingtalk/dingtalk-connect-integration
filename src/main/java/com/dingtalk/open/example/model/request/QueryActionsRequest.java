package com.dingtalk.open.example.model.request;

import com.dingtalk.open.example.model.DingRequest;
import com.dingtalk.open.example.model.response.QueryActionResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
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
public class QueryActionsRequest extends DingRequest<QueryActionResponse> {
    private String nextToken;
    private long maxResults;
    private final String connectorId;
    private final String connectorProviderCorpId;
    private List<String> integrationTypes;

    public QueryActionsRequest(Long nextToken, long maxResults, String connectorId, String connectorProviderCorpId, List<String> integrationTypes) {
        this.nextToken = String.valueOf(nextToken);
        this.maxResults = maxResults;
        this.connectorId = connectorId;
        this.connectorProviderCorpId = connectorProviderCorpId;
        this.integrationTypes = integrationTypes;
    }

    public QueryActionsRequest(String connectorId, String connectorProviderCorpId) {
        this(0L,50L, connectorId, connectorProviderCorpId, Collections.singletonList("basic"));
    }

    @Override
    protected RequestEntity<?> toEntity() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath("/v1.0/connector/assets/actions/search");
        return RequestEntity.post(uriBuilder.build().toUriString())
                .contentType(MediaType.APPLICATION_JSON)
                .body(this);
    }
}
