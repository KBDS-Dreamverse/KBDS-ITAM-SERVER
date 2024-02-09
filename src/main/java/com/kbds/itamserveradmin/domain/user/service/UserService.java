package com.kbds.itamserveradmin.domain.user.service;

import com.kbds.itamserveradmin.domain.assetRequest.repository.AssetRequestRepository;
import com.kbds.itamserveradmin.domain.contract.repository.ContractRepository;
import com.kbds.itamserveradmin.domain.user.entity.User;
import com.kbds.itamserveradmin.domain.user.repository.UserRepository;
import com.kbds.itamserveradmin.global.exception.BaseException;
import com.kbds.itamserveradmin.global.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User getUser(String userId){
        return userRepository.findById(userId).orElseThrow(
                () -> new BaseException(ErrorCode.NOT_FIND_USER) );
    }
}
