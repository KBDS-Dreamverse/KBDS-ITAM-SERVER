package com.kbds.itamserveradmin.domain.asset.repository;

import com.kbds.itamserveradmin.domain.asset.entity.Asset;
import com.kbds.itamserveradmin.domain.asset.entity.ManualLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManualLogRepository extends JpaRepository<ManualLog, String> {
    ManualLog findByAsset_AstId(String astId);
    List<ManualLog> findByAsset (Asset asset);
    ManualLog findByMnLogId (String mnLogId);
}
