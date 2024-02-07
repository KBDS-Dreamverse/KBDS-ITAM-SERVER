package com.kbds.itamserveradmin.domain.user.repository;

import com.kbds.itamserveradmin.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
