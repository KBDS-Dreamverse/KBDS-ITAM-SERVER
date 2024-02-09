package com.kbds.itamserveradmin.domain.contract.repository;

import com.kbds.itamserveradmin.domain.contract.entity.Contract;
import com.kbds.itamserveradmin.domain.contract.entity.SupplyType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplyTypeRepository extends JpaRepository<SupplyType, String> {
    /**
     * contId로 SupplyType 찾기
     * @param contId
     * @return
     */
    SupplyType findByCont_ContId(String contId);
}
