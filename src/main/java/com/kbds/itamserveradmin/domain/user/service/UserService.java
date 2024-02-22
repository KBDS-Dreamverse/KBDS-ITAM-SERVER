package com.kbds.itamserveradmin.domain.user.service;

import com.kbds.itamserveradmin.domain.department.entity.Department;
import com.kbds.itamserveradmin.domain.department.repository.DepartmentRepository;
import com.kbds.itamserveradmin.domain.user.dto.UserInfoRes;
import com.kbds.itamserveradmin.domain.user.entity.User;
import com.kbds.itamserveradmin.domain.user.entity.UserRole;
import com.kbds.itamserveradmin.domain.user.repository.UserRepository;
import com.kbds.itamserveradmin.global.exception.BaseException;
import com.kbds.itamserveradmin.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService {


    private final UserRepository userRepository;


    public UserInfoRes GetUserInfo(String userId) {
        log.info("userId : {}", userId);
        UserInfoRes userInfoRes = userRepository.findUserInfoWithCooperationAndDepartmentByUserId(userId);
        log.info(userInfoRes.toString());
        return userInfoRes;
    }



    public User getUser(String userId){
        return userRepository.findById(userId).orElseThrow(
                () -> new BaseException(ErrorCode.NOT_FIND_USER) );
    }
}



