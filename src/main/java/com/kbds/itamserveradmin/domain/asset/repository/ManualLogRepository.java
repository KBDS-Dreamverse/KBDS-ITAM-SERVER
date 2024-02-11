package com.kbds.itamserveradmin.domain.asset.repository;

import com.kbds.itamserveradmin.domain.asset.entity.ManualLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManualLogRepository extends JpaRepository<ManualLog, String> {
    ManualLog findByAsset_AstId(String astId);
}
