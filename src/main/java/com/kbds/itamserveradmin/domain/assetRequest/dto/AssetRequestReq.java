package com.kbds.itamserveradmin.domain.assetRequest.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetRequestReq {


//    @ApiModelProperty(notes = "계약서 ID",example = "")
//    private String contId;

    @ApiModelProperty(notes = "사용시작일",example = "2022-08-04")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate astReqStartDate;

    @ApiModelProperty(notes = "사용만료일",example = "2022-08-04")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate astReqEndDate;


    @ApiModelProperty(notes = "요청날짜",example = "2022-08-04")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime astReqMgDate;


    @ApiModelProperty(notes = "자산 요청 사유",example = "예적금 페이지 개발")
    private String astReqReason;


    @ApiModelProperty(notes = "요청자의 ID (사용자ID), 요청자가 누군지 식별",example = "")
    private String userId;
















}
