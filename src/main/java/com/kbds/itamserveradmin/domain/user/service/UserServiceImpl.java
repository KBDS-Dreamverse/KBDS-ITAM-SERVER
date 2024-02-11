package com.kbds.itamserveradmin.domain.user.service;

import com.kbds.itamserveradmin.domain.department.entity.Department;
import com.kbds.itamserveradmin.domain.department.repository.DepartmentRepository;
import com.kbds.itamserveradmin.domain.user.dto.UserInfoRes;
import com.kbds.itamserveradmin.domain.user.entity.User;
import com.kbds.itamserveradmin.domain.user.entity.UserRole;
import com.kbds.itamserveradmin.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    public UserInfoRes GetUserInfo(String userId){
        System.out.println("userId : "+ userId);
        UserInfoRes userInfoRes = userRepository.findUserInfoWithCooperationAndDepartmentByUserId(userId);
        System.out.println(userInfoRes.toString());
        return userInfoRes;

    }
}
