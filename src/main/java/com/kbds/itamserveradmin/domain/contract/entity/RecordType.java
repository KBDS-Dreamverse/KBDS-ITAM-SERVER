package com.kbds.itamserveradmin.domain.contract.entity;


import com.kbds.itamserveradmin.global.exception.BaseException;
import com.kbds.itamserveradmin.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum RecordType {
    FIELD_UPDATE("필드 변경"),
    TITLE_UPDATE("제목 변경"),
    ENTER("계약 체결");
    private final String value;

    public static RecordType getRecordTypeByName(String value) {
        return Arrays.stream(RecordType.values())
                .filter(r -> r.getValue().equals(value))
                .findAny().orElseThrow(() -> new BaseException(ErrorCode.CONTRACT_RECORD_TYPE_NOT_FOUND));
    }
}