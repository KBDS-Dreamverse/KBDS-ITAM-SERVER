package com.kbds.itamserveradmin.domain.asset.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AstTag {

    DAS("데이타시스템"), BNK("국민은행"), ISR("손해보험"), CRD("국민카드"), STK("증권"), LIF("라이프"),
    ASM("자산운용"), CAP("캐피탈"), RET("부동산신탁"), SAV("저축은행"), INV("인베스트먼트");

    @JsonValue
    private String value;
}
