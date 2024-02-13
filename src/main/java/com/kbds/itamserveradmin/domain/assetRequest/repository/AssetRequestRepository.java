package com.kbds.itamserveradmin.domain.assetRequest.repository;

import com.kbds.itamserveradmin.domain.assetRequest.entity.AssetRequest;
import com.kbds.itamserveradmin.domain.contract.entity.Contract;
import com.kbds.itamserveradmin.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetRequestRepository extends JpaRepository<AssetRequest, String> {
    List<AssetRequest> findByAstRequestUserUserId(String userId);
    AssetRequest findAssetRequestByContractAndAstRequestUser(Contract contract, User user);
}
