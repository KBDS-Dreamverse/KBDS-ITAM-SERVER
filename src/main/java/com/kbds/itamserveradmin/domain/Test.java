package com.kbds.itamserveradmin.domain;


import com.kbds.itamserveradmin.global.exception.BaseException;
import com.kbds.itamserveradmin.global.exception.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class Test {

    @PostMapping("/test")
    public ResponseEntity<String> test(){
        boolean test = true;
        System.out.println("시작");

        if(test) {
            throw new BaseException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok("성공");

    }
    
    
    
}
