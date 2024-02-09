package com.kbds.itamserveradmin.domain.contract.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PasswordReq {
    private String pw;
}
