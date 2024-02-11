package com.kbds.itamserveradmin.domain.user.service;

import com.kbds.itamserveradmin.domain.user.dto.UserInfoRes;

public interface UserService {
    public UserInfoRes GetUserInfo(String userId);
}
