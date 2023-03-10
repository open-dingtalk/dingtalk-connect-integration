package com.dingtalk.open.example.model.request;

import com.dingtalk.open.example.model.DingRequest;
import com.dingtalk.open.example.model.response.SyncDataResponse;
import lombok.Data;
import org.springframework.http.RequestEntity;

import java.util.List;

/**
 * 说明：
 *
 * @author donghuai.zjj
 * @date 2022/12/14
 */
public class SyncDataRequest extends DingRequest<SyncDataResponse> {
    private String appId;
    private List<TriggerDataDTO> triggerDataList;
    @Override
    protected RequestEntity<?> toEntity() {
        return RequestEntity.post("/v1.0/connector/triggers/data/sync")
                .body(this);
    }

    @Data
    public static class TriggerDataDTO {
        private String triggerId;
        private String customTriggerId;
    }
}
