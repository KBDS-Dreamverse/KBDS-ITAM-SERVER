package com.kbds.itamserveradmin.domain.user.controller;

import com.kbds.itamserveradmin.domain.user.dto.MainRes;
import com.kbds.itamserveradmin.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/kbitam")
public class UserController {
    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<MainRes>GetUserInfo(@RequestBody String userId){

        return ResponseEntity.ok(null);
    }
}
