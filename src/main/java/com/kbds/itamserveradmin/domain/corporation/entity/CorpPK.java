package com.kbds.itamserveradmin.domain.corporation.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum CorpPK {

    BNK("국민은행"),
    CRD("국민카드"),
    ISR("손해보험"),
    STK("증권"),
    LIF("라이프"),
    ASM("자산운용"),
    CAP("캐피탈"),
    RET("부동산신탁"),
    SAV("저축은행"),
    INV("인베스트먼트"),
    DAS("데이타시스템");


    @JsonValue
    private final String name;
}
