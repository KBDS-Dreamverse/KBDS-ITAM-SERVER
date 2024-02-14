package com.kbds.itamserveradmin.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UserRole {
    L0("L0","주임"),
    L1("L1","대리"),
    L2_1("L2","과장"),L2_2("L2","차장"),
    L3_1("L3","수석 차장"),L3_2("L3","팀장"),
    L4_1("L4","부장"),L4_2("L4","본부장");

    private final String code;
    private final String value;
}