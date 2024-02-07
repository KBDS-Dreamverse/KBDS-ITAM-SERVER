package com.kbds.itamserveradmin.domain.assetRequest.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LicenseCategory {
    CAL(1,"서버접속"),SITE(2,"사이트"),ONE_PC_ONE_COPY(3,"1PC1COPY");

    private int code;

    @JsonValue
    private String value;
}
