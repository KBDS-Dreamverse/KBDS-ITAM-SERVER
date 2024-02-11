package com.kbds.itamserveradmin.domain.asset.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AstTag {

    BANK("은행"), INSURANCE("보험"), INVESTMENT("투자"), CAPITAL("캐피탈"), REAL_ESTATE("부동산");

    @JsonValue
    private String value;
}
