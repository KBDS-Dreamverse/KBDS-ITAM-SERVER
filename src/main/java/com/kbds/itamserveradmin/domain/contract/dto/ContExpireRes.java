package com.kbds.itamserveradmin.domain.contract.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ContExpireRes {

    private String contStartDate;

    private String contEndDate;

    private Long remainingDays;
}
