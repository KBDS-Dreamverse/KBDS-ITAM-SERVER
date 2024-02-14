package com.kbds.itamserveradmin.domain.department.entity;


import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DeptPK {

    DNB("디지털신사업부"),
    RCP("수신상품부"),
    GBP("글로벌플랫폼부"),
    CSD("고객만족부"),
    ESD("직원만족부");


    @JsonValue
    private final String name;

}
