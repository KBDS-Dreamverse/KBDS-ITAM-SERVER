package com.kbds.itamserveradmin.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


/*
 * 에러 코드 관리
 */
@Getter
public enum ErrorCode {

    // contract
    CONTRACT_RECORD_TYPE_NOT_FOUND(HttpStatus.NOT_FOUND, "C-001", "활동 기록 타입을 찾을 수 없습니다."),
    CONTRACT_NOT_FOUND(HttpStatus.NOT_FOUND, "C-002", "해당 계약을 찾을 수 없습니다."),
    CONTRACT_IS_NOT_CAL_LIC(HttpStatus.FORBIDDEN, "C-003", "해당 계약은 cal 라이선스가 아닙니다."),
    CONTRACT_TYPE_NOT_FOUND(HttpStatus.NOT_FOUND, "C-004", "계약 종류(공급형태, 기간, 사용자수)를 찾을 수 없습니다."),

    // corporation
    CORPORATION_NOT_FOUND(HttpStatus.NOT_FOUND, "P-001", "id에 해당하는 업체가 존재하지 않습니다."),

    //기타
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "I-001", "내부 에러가 발생했습니다."),

    // cal key 조회를 위한 pw
    PASSWORD_INCORRECT(HttpStatus.UNAUTHORIZED, "P-001", "비밀번호가 틀립니다."),

    // asset
    ASSET_IS_NOT_INUSE(HttpStatus.CONFLICT, "A-001", "사용중인 상태가 아닙니다."),
    ASSET_IS_EXPIRE(HttpStatus.FORBIDDEN, "A-002", "사용이 만료되었습니다."),
    ASSET_NOT_FOUND(HttpStatus.NOT_FOUND, "A-003", "id에 일치하는 자산을 찾을 수 없습니다."),

    // new asset
    NEW_ASSET_REQUEST_NOT_FOUND(HttpStatus.NOT_FOUND, "N-001", "id에 일치하는 자산 요청 기록을 찾을 수 없습니다."),

    // user
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "U-001", "id에 일치하는 유저를 찾을 수 없습니다.");




    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String code, String message) { // ErrorCode 에서 각 해당하는 코드를 생성자로 맵핑
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }





}
