package com.kbds.itamserveradmin.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


/*
 * 에러 코드 관리
 */
@Getter
public enum ErrorCode {


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
