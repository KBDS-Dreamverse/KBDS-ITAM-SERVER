package com.kbds.itamserveradmin.global.constant;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleType {
    GUEST("ROLE_GUEST", "외부 사용자"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;
}
