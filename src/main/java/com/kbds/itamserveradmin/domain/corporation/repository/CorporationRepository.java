package com.kbds.itamserveradmin.domain.corporation.repository;

import com.kbds.itamserveradmin.domain.corporation.entity.Corporation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CorporationRepository extends JpaRepository<Corporation,String> {
    Corporation findCorporationByCorpName(String CorpName);

}
