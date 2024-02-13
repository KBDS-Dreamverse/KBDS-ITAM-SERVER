package com.kbds.itamserveradmin.domain.user.repository;

import com.kbds.itamserveradmin.domain.user.dto.UserInfoRes;
import com.kbds.itamserveradmin.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,String> {
    @Query("SELECT NEW com.kbds.itamserveradmin.domain.user.dto.UserInfoRes(u.userId, u.userName, u.userPhoto, u.userRole, d.deptName, c.corpName) " +
            "FROM User u JOIN u.department d JOIN u.corporation c " +
            "WHERE u.userId = :userId")
    UserInfoRes findUserInfoWithCorporationAndDepartmentByUserId(@Param("userId") String userId);
}
