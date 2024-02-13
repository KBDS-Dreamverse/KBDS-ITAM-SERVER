package com.kbds.itamserveradmin.global.exception.business;


import com.kbds.itamserveradmin.global.exception.BaseException;
import com.kbds.itamserveradmin.global.exception.ErrorCode;

public class EntityNotFoundException extends BaseException {

    public EntityNotFoundException(ErrorCode status) {
        super(status);
    }
}