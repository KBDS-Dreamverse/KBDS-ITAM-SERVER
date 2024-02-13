package com.kbds.itamserveradmin.domain.asset.repository;

import com.kbds.itamserveradmin.domain.asset.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, String> {
    Asset findAssetByAstId(String astId);

}
