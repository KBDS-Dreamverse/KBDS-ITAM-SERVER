package com.kbds.itamserveradmin.domain.corporation.repository;

import com.kbds.itamserveradmin.domain.corporation.entity.Corporation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorporationRepository extends JpaRepository<Corporation,Integer> {
}
