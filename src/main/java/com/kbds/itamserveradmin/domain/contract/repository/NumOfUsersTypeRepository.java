package com.kbds.itamserveradmin.domain.contract.repository;

import com.kbds.itamserveradmin.domain.contract.entity.NumOfUsersType;
import com.kbds.itamserveradmin.domain.contract.entity.PeriodType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NumOfUsersTypeRepository extends JpaRepository<NumOfUsersType, String> {
    NumOfUsersType findByCont_ContId(String contId);

}
