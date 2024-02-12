package com.kbds.itamserveradmin.domain.assetRequest.repository;

import com.kbds.itamserveradmin.domain.assetRequest.dto.AstReqSearchReq;
import com.kbds.itamserveradmin.domain.assetRequest.dto.AstReqSearchRes;
import com.kbds.itamserveradmin.domain.assetRequest.entity.AssetRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AssetRequestRepositoryCustom {


    Page<AstReqSearchRes> search(AstReqSearchReq req, Pageable pageable);

    List<AssetRequest> test(String userId);

}
