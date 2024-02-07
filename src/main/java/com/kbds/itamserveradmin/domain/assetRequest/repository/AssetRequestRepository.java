package com.kbds.itamserveradmin.domain.assetRequest.repository;

import com.kbds.itamserveradmin.domain.assetRequest.entity.AssetRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AssetRequestRepository extends JpaRepository<AssetRequest,String> {
}
