package com.kbds.itamserveradmin.domain.user.repository;

import com.kbds.itamserveradmin.domain.user.dto.UserInfoRes;
import com.kbds.itamserveradmin.domain.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User,String> {

//    @Query("SELECT u FROM User u WHERE u.userId =:user_id")
//    User findByUserId(@Param("user_id") String userId);


    @Query("SELECT NEW com.kbds.itamserveradmin.domain.user.dto.UserInfoRes(u.userId, u.userName, u.userPhoto, u.userRole, d.deptName, c.corpName) " +
            "FROM User u JOIN u.department d JOIN u.corporation c " +
            "WHERE u.userId = :userId")
    UserInfoRes findUserInfoWithCooperationAndDepartmentByUserId(@Param("userId") String userId);
}
