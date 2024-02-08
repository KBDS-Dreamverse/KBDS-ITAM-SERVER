package com.kbds.itamserveradmin.domain.assetRequest.repository;

import com.kbds.itamserveradmin.domain.assetRequest.entity.AssetRequestManageLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRequestManageLogRepository extends JpaRepository<AssetRequestManageLog, String> {
}
