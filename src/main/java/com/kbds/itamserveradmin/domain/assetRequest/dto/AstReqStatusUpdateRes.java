package com.kbds.itamserveradmin.domain.assetRequest.dto;


import com.kbds.itamserveradmin.domain.assetRequest.entity.RequestStatus;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class AstReqStatusUpdateRes {

    private String astReqLogId;
    private String astReqId;
    private String status;

    public static AstReqStatusUpdateRes of(String astReqLogId, String astReqId, RequestStatus status){
        return AstReqStatusUpdateRes.builder()
                .astReqId(astReqId)
                .astReqLogId(astReqLogId)
                .status(status.getValue())
                .build();

    }
}
