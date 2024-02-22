package com.kbds.itamserveradmin.domain.assetRequest.repository;


import com.kbds.itamserveradmin.domain.assetRequest.entity.AssetRequestLog;

public interface AssetRequestLogRepositoryCustom {

    AssetRequestLog getRecentLog(String astReqId);


}
