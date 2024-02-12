package com.kbds.itamserveradmin.domain.assetRequest.repository;


import com.kbds.itamserveradmin.domain.asset.entity.QAsset;
import com.kbds.itamserveradmin.domain.assetRequest.dto.AstReqSearchReq;
import com.kbds.itamserveradmin.domain.assetRequest.dto.AstReqSearchRes;
import com.kbds.itamserveradmin.domain.assetRequest.entity.AssetRequest;
import com.kbds.itamserveradmin.domain.assetRequest.entity.QAssetRequest;
import com.kbds.itamserveradmin.domain.contract.entity.QContract;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class AssetRequestRepositoryImpl implements AssetRequestRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    QAssetRequest astRequest = new QAssetRequest("AssetRequest");


    @Override
    public Page<AstReqSearchRes> search(AstReqSearchReq req, Pageable pageable){


        List<AstReqSearchRes> contents = queryFactory
                .select(Projections.fields(AstReqSearchRes.class,
                        QAsset.asset.astName,
                        QAssetRequest.assetRequest.astReqId,
                        QAssetRequest.assetRequest.astReqMgDate,
                        QAssetRequest.assetRequest.astReqStatus))
                .from(QAssetRequest.assetRequest)
                .join(QAssetRequest.assetRequest.contract,QContract.contract)
                .join(QContract.contract.ast,QAsset.asset)
                .where(astRequestUser(req.getUserId()) , UserRequestCond(req.getStart(),req.getEnd()))
                .orderBy(QAssetRequest.assetRequest.astReqMgDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        Long total = queryFactory
                .select(QAssetRequest.assetRequest.count())
                .from(QAssetRequest.assetRequest)
                .where(astRequestUser(req.getUserId()) , UserRequestCond(req.getStart(),req.getEnd()))
                .fetchOne();

        System.out.println("total>>"+total);




        return new PageImpl<>(contents,pageable,total);

        //return PageableExecutionUtils.getPage(contents,pageable,countQuery::fetchCount);
    }

    private BooleanExpression astRequestUser(String userId){

        return QAssetRequest.assetRequest.astRequestUser.userId.eq(userId);
    }

    private BooleanExpression UserRequestCond(LocalDateTime start, LocalDateTime end){

        if(start != null){
            if(end == null){
                end = LocalDateTime.now();
            }
            start = start.with(LocalTime.of(0, 0, 0)); // 시분초를 0시로 설정
            end = end.with(LocalTime.MAX); // 자정으로 설정
            System.out.println("시간 SET : "+"start : "+start+" end: " + end);
            return QAssetRequest.assetRequest.astReqMgDate.between(start,end);
        }

        return null;
    }

    @Override
    public List<AssetRequest> test(String userId) {
        QAssetRequest assetRequest = QAssetRequest.assetRequest;
        return queryFactory.selectFrom(assetRequest)
                .where(assetRequest.astRequestUser.userId.eq(userId))
                .fetch();

//        AssetRequest c = queryFactory
//                .selectFrom()
//                .where(QAssetRequest.assetRequest.astRequestUser.userId.eq(userId))
//                .limit(1L)
//                .fetchOne();



//        return c;
    }
}
