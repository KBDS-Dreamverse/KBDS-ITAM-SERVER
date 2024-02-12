package com.kbds.itamserveradmin.domain.asset.repository;

import com.kbds.itamserveradmin.domain.asset.entity.Asset;
import com.kbds.itamserveradmin.domain.corporation.entity.CorpPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public interface AssetRepository extends JpaRepository<Asset, String> {



//    @Query("SELECT a  " +
//            "FROM Asset a " +
//            "Where a.corp.corpId = :corp_id " +
//            "order by astId desc")
//    Asset findCorpCntById(@Param("corp_id") String corp_id);

    @Query(value = "SELECT max(a.astId) " +
            "FROM Asset a " + // 여기서 공백을 추가해야 함
            "WHERE a.corp.corpId = :corp_id " + // WHERE 절에 공백을 추가하고 corp_id의 앞에 콜론(:)을 빼먹지 않아야 함
            "ORDER BY a.astId DESC") // ORDER BY 구문을 추가하고 astId 앞에 a.를 빼먹지 않아야 함
    String findCorpCntById(@Param("corp_id") String corp_id);
}
