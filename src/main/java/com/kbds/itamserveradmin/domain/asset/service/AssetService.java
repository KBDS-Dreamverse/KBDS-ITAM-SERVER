package com.kbds.itamserveradmin.domain.asset.service;

import com.kbds.itamserveradmin.domain.asset.dto.AssetRes;
import com.kbds.itamserveradmin.domain.asset.entity.Asset;
import com.kbds.itamserveradmin.domain.contract.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AssetService {

    private final ContractService contractService;

    @Transactional
    public AssetRes getInfo(String contId) {
        Asset asset = contractService.getAstIdByContId(contId);
        if (asset == null) {
            return null;
        }
        return AssetRes.assetInfo(asset);
    }
}
