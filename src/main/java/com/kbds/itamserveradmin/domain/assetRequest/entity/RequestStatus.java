package com.kbds.itamserveradmin.domain.assetRequest.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RequestStatus {
    APPROVAL_WAIT("승인대기"),APPROVAL("승인"),IN_USE("사용중"),APPROVAL_REJECTED("승인거절"),EXPIRED("폐기"),NOT_IN_USE("미사용중");

    @JsonValue
    private String value;
}
