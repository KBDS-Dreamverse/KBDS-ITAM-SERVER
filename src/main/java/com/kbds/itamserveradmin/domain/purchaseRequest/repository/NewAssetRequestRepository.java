package com.kbds.itamserveradmin.domain.purchaseRequest.repository;

import com.kbds.itamserveradmin.domain.purchaseRequest.entity.NewAssetRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewAssetRequestRepository extends JpaRepository<NewAssetRequest,String> {
}
