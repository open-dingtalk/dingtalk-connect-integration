package com.dingtalk.open.example.model.request;

import com.dingtalk.open.example.model.DingRequest;
import com.dingtalk.open.example.model.response.GetActionDetailResponse;
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
 * @date 2022/12/27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GetActionDetailRequest extends DingRequest<GetActionDetailResponse> {
    private final String connectAssetUri;
    @Override
    protected RequestEntity<?> toEntity() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath("/v1.0/connector/assets/actions/details/query");
        return RequestEntity.post(uriBuilder.build().toUriString())
                .contentType(MediaType.APPLICATION_JSON)
                .body(this);
    }
}
