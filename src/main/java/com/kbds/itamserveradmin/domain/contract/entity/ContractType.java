package com.kbds.itamserveradmin.domain.contract.entity;

import com.kbds.itamserveradmin.global.exception.BaseException;
import com.kbds.itamserveradmin.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ContractType {
    LICENSE_SUPPLY("라이선스 공급형태"),
    LICENSE_PERIOD("라이선스 기간"),
    NUMBER_OF_USERS("라이선스 사용자 수");
    private final String value;

    public static ContractType getContractTypeByName(String value) {
        return Arrays.stream(ContractType.values())
                .filter(r -> r.getValue().equals(value))
                .findAny().orElseThrow(() -> new BaseException(ErrorCode.CONTRACT_TYPE_NOT_FOUND));
    }
}
