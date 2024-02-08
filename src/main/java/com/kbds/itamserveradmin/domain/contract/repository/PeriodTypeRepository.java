package com.kbds.itamserveradmin.domain.contract.repository;

import com.kbds.itamserveradmin.domain.contract.entity.Contract;
import com.kbds.itamserveradmin.domain.contract.entity.PeriodType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeriodTypeRepository extends JpaRepository<PeriodType, String> {
}
