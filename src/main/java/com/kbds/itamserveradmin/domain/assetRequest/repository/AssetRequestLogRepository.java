package com.kbds.itamserveradmin.domain.assetRequest.repository;

import com.kbds.itamserveradmin.domain.assetRequest.entity.AssetRequestLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRequestLogRepository extends JpaRepository<AssetRequestLog, String> {
}
