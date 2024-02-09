package com.kbds.itamserveradmin.domain.user.service;

import com.kbds.itamserveradmin.domain.user.dto.MainRes;
import com.kbds.itamserveradmin.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, UserRepository userRepository1) {
        super(userRepository);
        this.userRepository = userRepository1;
    }

    public MainRes GetUserInfo(String userId){
        return null;

    }
}
