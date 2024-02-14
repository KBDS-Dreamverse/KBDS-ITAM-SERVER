package com.kbds.itamserveradmin.domain.assetRequest.repository;


import com.kbds.itamserveradmin.domain.assetRequest.entity.AssetRequestLog;
import com.kbds.itamserveradmin.domain.assetRequest.entity.QAssetRequest;
import com.kbds.itamserveradmin.domain.assetRequest.entity.QAssetRequestLog;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
@Repository
@RequiredArgsConstructor
public class AssetRequestLogRepositoryImpl implements AssetRequestLogRepositoryCustom {
    private final JPAQueryFactory queryFactory;


    @Override
    public AssetRequestLog getRecentLog(String astReqId) {
        AssetRequestLog result = queryFactory
                .selectFrom(QAssetRequestLog.assetRequestLog)
                .where(QAssetRequest.assetRequest.astReqId.eq(astReqId))
                .orderBy(QAssetRequest.assetRequest.astReqId.desc())
                .limit(1)
                .fetchOne();

        return result;
    }
}
