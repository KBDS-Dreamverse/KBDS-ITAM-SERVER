package com.kbds.itamserveradmin.domain.corporation.repository;

import com.kbds.itamserveradmin.domain.corporation.entity.Corporation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CorporationRepository extends JpaRepository<Corporation,Integer> {
}
