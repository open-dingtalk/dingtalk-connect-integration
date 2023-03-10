package com.dingtalk.open.example.model.request;

import com.dingtalk.open.example.model.DingRequest;
import com.dingtalk.open.example.model.response.UpdateInvokerResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 说明：创建可调用实例
 *
 * @author donghuai.zjj
 * @date 2022/12/28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CreateInvokerRequest extends DingRequest<UpdateInvokerResponse> {
    private final String connectAssetUri;

    private final String instanceKey;
    @Override
    protected RequestEntity<?> toEntity() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath("/v1.0/connector/instances");
        return RequestEntity.post(uriBuilder.build().toUriString())
                .contentType(MediaType.APPLICATION_JSON)
                .body(this);
    }
}
