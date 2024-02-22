package com.kbds.itamserveradmin.domain.assetRequest.repository;

import com.kbds.itamserveradmin.domain.assetRequest.entity.AssetRequest;
import com.kbds.itamserveradmin.domain.contract.entity.Contract;
import com.kbds.itamserveradmin.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRequestRepository extends JpaRepository<AssetRequest, String>,AssetRequestRepositoryCustom  {
    List<AssetRequest> findByAstRequestUserUserId(String userId);
    AssetRequest findAssetRequestByContractAndAstRequestUser(Contract contract, User user);

}
