package com.kbds.itamserveradmin.domain.user.service;

import com.kbds.itamserveradmin.domain.user.dto.MainRes;
import com.kbds.itamserveradmin.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    public MainRes GetUserInfo(String userId){
        return null;

    }
}
