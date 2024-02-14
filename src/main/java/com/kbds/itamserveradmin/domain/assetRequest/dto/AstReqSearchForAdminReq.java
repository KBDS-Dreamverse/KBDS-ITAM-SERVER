package com.kbds.itamserveradmin.domain.assetRequest.dto;

import com.kbds.itamserveradmin.domain.assetRequest.entity.RequestMangeStatus;
import com.kbds.itamserveradmin.domain.assetRequest.entity.RequestStatus;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AstReqSearchForAdminReq {


    private String userId;
    private LocalDateTime start;
    private LocalDateTime end;

    private String userName;
    private String astReqDept;

    private String astReqMgStatus;

}
