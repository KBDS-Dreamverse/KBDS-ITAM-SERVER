package com.kbds.itamserveradmin.domain.contract.repository;

import com.kbds.itamserveradmin.domain.contract.entity.CALKey;
import com.kbds.itamserveradmin.domain.contract.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CALKeyRepository extends JpaRepository<CALKey, String> {
}
