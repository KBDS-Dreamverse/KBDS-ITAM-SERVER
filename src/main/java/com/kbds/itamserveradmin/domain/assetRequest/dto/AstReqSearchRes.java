package com.kbds.itamserveradmin.domain.assetRequest.dto;


import com.kbds.itamserveradmin.domain.assetRequest.entity.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class AstReqSearchRes {


    private String astName; //자산 이름

    private String astReqId; //id
    private LocalDateTime astReqMgDate; //요청날짜
    private RequestStatus astReqStatus; //요청상태





}
