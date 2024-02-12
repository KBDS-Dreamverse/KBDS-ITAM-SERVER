package com.kbds.itamserveradmin.domain.assetRequest.dto;


import com.kbds.itamserveradmin.domain.assetRequest.entity.RequestMangeStatus;
import com.kbds.itamserveradmin.domain.assetRequest.entity.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class AstReqSearchForAdminRes {


    private String astReqName; //자산 이름

    private String astReqId; //id
    private LocalDateTime astReqMgDate; //요청날짜
    private RequestMangeStatus astReqMgStatus; //요청태관리상태

    private String userName; //이름
    private String userId; //사번
    private String astReqDept; // 요청부서
    private String contId;






}
