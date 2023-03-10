package com.dingtalk.open.example.model.request;

import com.dingtalk.open.example.model.DingRequest;
import com.dingtalk.open.example.model.response.UpdateInvokerResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.http.RequestEntity;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 说明：
 *
 * @author donghuai.zjj
 * @date 2022/12/28
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class DeleteInvokerRequest extends DingRequest<UpdateInvokerResponse> {
    private final String connectAssetUri;

    private final String instanceKey;
    @Override
    protected RequestEntity<?> toEntity() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath("/v1.0/connector/instances");
        uriBuilder.queryParam("connectAssetUri", connectAssetUri);
        uriBuilder.queryParam("instanceKey", instanceKey);
        return RequestEntity.delete(uriBuilder.build().toUriString()).build();
    }
}