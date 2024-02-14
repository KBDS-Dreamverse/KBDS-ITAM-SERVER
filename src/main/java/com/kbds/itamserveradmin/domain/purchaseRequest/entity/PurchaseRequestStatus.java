package com.kbds.itamserveradmin.domain.purchaseRequest.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PurchaseRequestStatus {
    REQUEST("요청"),IN_CONTRACT("계약중"),CONTRACT_COMPLETED("계약완료"),REQUEST_REJECTED("요청거절"),REQUEST_CANCELED("요청취소");

    @JsonValue
    private String value;
}
