package com.kbds.itamserveradmin.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


/*
 * 에러 코드 관리
 */
@Getter
public enum ErrorCode {

    //user
    NOT_FIND_USER(HttpStatus.BAD_REQUEST,"U-001","사용자를 찾을 수 없습니다."),

    // contract
    NOT_FIND_CONTRACT(HttpStatus.BAD_REQUEST,"C-001","계약을 찾을 수 없습니다."),
    CONTRACT_RECORD_TYPE_NOT_FOUND(HttpStatus.NOT_FOUND, "C-002", "활동 기록 타입을 찾을 수 없습니다."),
    CONTRACT_IS_NOT_IN_OPERATION(HttpStatus.BAD_REQUEST,"C-003","운영중인 자산이 아닙니다."),

    //Log
    FAIL_SAVED_ASSETREQUESTLOG(HttpStatus.INTERNAL_SERVER_ERROR,"L-001","자산 요청 로그 저장 실패"),
    FAIL_SAVED_ASSETREQUESTMGLOG(HttpStatus.INTERNAL_SERVER_ERROR,"L-002","자산 요청 관리 로그 저장 실패"),


    //기타
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "I-001", "내부 에러가 발생했습니다.");




    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String code, String message) { // ErrorCode 에서 각 해당하는 코드를 생성자로 맵핑
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }





}
