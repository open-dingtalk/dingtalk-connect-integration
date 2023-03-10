package com.dingtalk.open.example.model.response;

import com.dingtalk.open.example.model.DingResponse;
import lombok.Data;

import java.util.List;

/**
 * 说明：
 *
 * @author donghuai.zjj
 * @date 2022/12/14
 */
@Data
public class SyncDataResponse extends DingResponse {
    private List<TriggerResultDTO> list;


    @Data
    public static class TriggerResultDTO {
        String triggerId;
        boolean success;
    }
}
