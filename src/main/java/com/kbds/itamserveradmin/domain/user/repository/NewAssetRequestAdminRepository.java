package com.kbds.itamserveradmin.domain.user.repository;

import com.kbds.itamserveradmin.domain.purchaseRequest.entity.NewAssetRequest;
import com.kbds.itamserveradmin.domain.user.entity.NewAssetReqeustAdminId;
import com.kbds.itamserveradmin.domain.user.entity.NewAssetRequestAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewAssetRequestAdminRepository extends JpaRepository<NewAssetRequestAdmin, NewAssetReqeustAdminId> {
}
