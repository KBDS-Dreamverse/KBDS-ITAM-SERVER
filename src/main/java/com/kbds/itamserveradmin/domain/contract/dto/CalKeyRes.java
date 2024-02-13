package com.kbds.itamserveradmin.domain.contract.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CalKeyRes {

    private String calKey;
    private String calKeyStatus;

}
