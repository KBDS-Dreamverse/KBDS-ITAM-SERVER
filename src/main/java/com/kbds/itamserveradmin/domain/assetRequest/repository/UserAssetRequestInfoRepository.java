package com.kbds.itamserveradmin.domain.assetRequest.repository;

import com.kbds.itamserveradmin.domain.assetRequest.entity.UserAssetRequestInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface UserAssetRequestInfoRepository extends JpaRepository<UserAssetRequestInfo, String> {
    UserAssetRequestInfo findByAssetRequest_AstReqId(String astReqId);

//    @Query("SELECT uari FROM UserAssetRequestInfo uari " +
//            "JOIN FETCH uari.assetRequest ar " +
//            "JOIN FETCH uari.licenseKey lk " +
//            "JOIN FETCH uari.calKey ck " +
//            "WHERE ar.astReqId = :astReqId")
//    UserAssetRequestInfo findByAssetRequest_AstReqIdWithLicenseKeyAndCALKey(@Param("astReqId") String astReqId);
}
