package com.kbds.itamserveradmin.domain.user.repository;

import com.kbds.itamserveradmin.domain.user.entity.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLogRepository extends JpaRepository<UserLog,String> {
}
