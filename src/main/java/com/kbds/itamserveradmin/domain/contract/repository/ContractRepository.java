package com.kbds.itamserveradmin.domain.contract.repository;

import com.kbds.itamserveradmin.domain.contract.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, String> {
    Contract findByContId (String contId);
}
