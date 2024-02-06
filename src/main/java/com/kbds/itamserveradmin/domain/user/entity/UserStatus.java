package com.kbds.itamserveradmin.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {
    EMPLOYMENT("재직"),RESIGNATION("휴직"),LEAVE("퇴직");

    @JsonValue
    private String value;
}
