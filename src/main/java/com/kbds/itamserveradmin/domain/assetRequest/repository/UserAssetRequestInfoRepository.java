package com.kbds.itamserveradmin.domain.assetRequest.repository;

import com.kbds.itamserveradmin.domain.assetRequest.entity.UserAssetRequestInfo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserAssetRequestInfoRepository extends JpaRepository<UserAssetRequestInfo, String> {
}
