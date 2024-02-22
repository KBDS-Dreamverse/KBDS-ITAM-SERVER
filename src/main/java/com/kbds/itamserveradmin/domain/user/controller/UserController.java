package com.kbds.itamserveradmin.domain.user.controller;

import com.kbds.itamserveradmin.domain.user.dto.UserInfoRes;
import com.kbds.itamserveradmin.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins ="*", allowedHeaders = "*")
@RequestMapping("/kbitam")
public class UserController {

    private final UserService userService;

    @GetMapping("/userInfo")
    public ResponseEntity<UserInfoRes> getUserInfo(@RequestParam("userId") String userId) {
        return ResponseEntity.ok(userService.GetUserInfo(userId));
    }
}
