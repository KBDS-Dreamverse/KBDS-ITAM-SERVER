package com.kbds.itamserveradmin.domain.contract.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KeyStatus {
    IN_USE("사용중"),NOT_IN_USE("미사용중");

    @JsonValue
    private String value;
}
