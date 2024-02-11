package com.kbds.itamserveradmin.domain.contract.repository;

import com.kbds.itamserveradmin.domain.contract.entity.Contract;
import com.kbds.itamserveradmin.domain.contract.entity.ContractRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRecordRepository extends JpaRepository<ContractRecord, String> {
}
