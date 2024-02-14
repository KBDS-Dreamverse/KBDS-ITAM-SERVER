package com.kbds.itamserveradmin.domain.assetRequest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RequestMangeStatus {
    APPROVAL_WAIT("승인대기"),APPROVAL("승인"),APPROVAL_REJECTED("승인거절");
    private String value;
}