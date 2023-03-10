package com.dingtalk.open.example.model.request;

import com.dingtalk.open.example.model.DingRequest;
import com.dingtalk.open.example.model.response.InvokeInstanceResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 说明：
 *
 * @author donghuai.zjj
 * @date 2022/12/29
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InvokeInstanceRequest extends DingRequest<InvokeInstanceResponse> {
    private final String connectAssetUri;

    private final String instanceKey;

    private final String inputJsonString;

    @Override
    protected RequestEntity<?> toEntity() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath("/v1.0/connector/instances/invoke");
        return RequestEntity.post(uriBuilder.build().toUriString())
                .contentType(MediaType.APPLICATION_JSON)
                .body(this);
    }
}
