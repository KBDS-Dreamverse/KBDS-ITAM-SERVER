package com.kbds.itamserveradmin.domain.contract.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class RegisterContractReq {

    @Schema(type = "String", description = "계약명", example = "TV Calibration Software", required = true)
    @NotBlank(message = "C0001")
    private String contractName;

    @Schema(type = "String", description = "계약 대상 자산", example = "1", required = true)
    @NotBlank(message = "C0001")
    private String assetId;

    @Schema(type = "String", description = "계약 업체", example = "1", required = true)
    @NotBlank(message = "C0001")
    private String corporationId;

    @Schema(type = "String", description = "계약 담당자", example = "김민기", required = true)
    @NotBlank(message = "C0001")
    private String contractAdminName;

    @Schema(type = "String", description = "가격", example = "46,787,027.7931", required = true)
    @NotBlank(message = "C0001")
    private int contractPrice;

    @Schema(type = "String", description = "시작일", example = "2022-09-25", required = true)
    @NotBlank(message = "C0001")
    private String startDate;

    @Schema(type = "String", description = "종료일", example = "2024-09-25", required = true)
    @NotBlank(message = "C0001")
    private String endDate;

    @Schema(type = "String", description = "기타사항", example = "2022-09-25 등록", required = true)
    @NotBlank(message = "C0001")
    private String description;
}
