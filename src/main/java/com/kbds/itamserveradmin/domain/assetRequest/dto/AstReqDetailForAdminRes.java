package com.kbds.itamserveradmin.domain.assetRequest.dto;


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
    private String requestStatus;

    private UserInfoDto user;

    private LocalDateTime astReqStartDate;
    private LocalDateTime astReqEndDate;

    private String astReqReason;

    private String contId; //나중에 에셋정보보기로 이어지기 위함.



}
