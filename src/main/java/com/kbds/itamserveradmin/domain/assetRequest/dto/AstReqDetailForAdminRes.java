package com.kbds.itamserveradmin.domain.assetRequest.dto;


import com.kbds.itamserveradmin.domain.assetRequest.entity.AssetRequestManage;
import com.kbds.itamserveradmin.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class AstReqDetailForAdminRes {

    private String astReqName;
    private String astReqMgStatus;

    private UserInfoDto user;

    private LocalDateTime astReqStartDate;
    private LocalDateTime astReqEndDate;

    private String astReqReason;

    private String contId; //나중에 에셋정보보기로 이어지기 위함.

    public final AstReqDetailForAdminRes of(User u , AssetRequestManage a, String contId){
        return AstReqDetailForAdminRes.builder()
                .astReqName(a.getAstReqName())
                .astReqMgStatus(a.getAstReqMgStatus().getValue())
                .user(UserInfoDto.from(u))
                .astReqStartDate(a.getAssetRequest().getAstReqStartDate())
                .astReqEndDate(a.getAssetRequest().getAstReqEndDate())
                .astReqReason(a.getAssetRequest().getAstReqReason())
                .contId(contId)
                .build();

    }





}
