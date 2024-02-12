package com.kbds.itamserveradmin.domain.assetRequest.dto;


import com.kbds.itamserveradmin.domain.assetRequest.entity.AssetRequest;
import com.kbds.itamserveradmin.domain.assetRequest.entity.AssetRequestManage;
import com.kbds.itamserveradmin.domain.user.entity.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AstReqDetailRes {

    private String astReqName;
    private String requestStatus;

    private UserInfoDto user;

    private LocalDateTime astReqStartDate;
    private LocalDateTime astReqEndDate;

    private String astReqReason;

    private List<AstReqAdminList> astAdminList;



    public static AstReqDetailRes of(String astReqName,String requestStatus, LocalDateTime astReqStartDate, LocalDateTime astReqEndDate,String astReqReason, User u, List<AstReqAdminList> astAdminList){
        return AstReqDetailRes.builder()
                .astReqName(astReqName)
                .requestStatus(requestStatus)
                .user(UserInfoDto.from(u))
                .astReqStartDate(astReqStartDate)
                .astReqEndDate(astReqEndDate)
                .astReqReason(astReqReason)
                .astAdminList(astAdminList)
                .build();



    }






}
