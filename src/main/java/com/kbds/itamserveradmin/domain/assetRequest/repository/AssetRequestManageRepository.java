package com.kbds.itamserveradmin.domain.assetRequest.repository;

import com.kbds.itamserveradmin.domain.assetRequest.entity.AssetRequestManage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRequestManageRepository extends JpaRepository<AssetRequestManage, String> {


//    @Query("select am " +
//            "from AssetRequestManage am " +
//            "join fetch am.assetRequest ar " +
//            "join fetch am.assetAdmin u " +
//            "where am.ast_req_id = :astReqId and am.assetRequest.astReqId = ar.astReqId and am.assetAdmin.astAdminId = u.astAdminId  ")
//    List<AssetRequestManage> findAssetRequestManageById(@Param("astReqId")String astReqId);

    @Query("SELECT ar FROM AssetRequestManage ar" +
            " JOIN FETCH ar.assetRequest" +
            " JOIN FETCH ar.assetAdmin " +
            " WHERE ar.assetRequest.astReqId = :astReqId")
    public List<AssetRequestManage> findByAstReqIdWithAssetRequest(@Param("astReqId") String astReqId);



    @Query("SELECT ar FROM AssetRequestManage ar " +
            "WHERE ar.assetRequest.astReqId = :astReqId ")
    public List<AssetRequestManage> findByAstReqId(@Param("astReqId") String astReqId);


    @Query("SELECT ar FROM AssetRequestManage ar " +
            "WHERE ar.astReqMgId = :astReqMgId ")
    AssetRequestManage findByAstReqMgId(@Param("astReqMgId") String astReqMgId);



}
