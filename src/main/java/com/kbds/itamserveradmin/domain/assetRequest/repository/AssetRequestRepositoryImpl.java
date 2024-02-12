package com.kbds.itamserveradmin.domain.assetRequest.repository;


import com.kbds.itamserveradmin.domain.asset.entity.QAsset;
import com.kbds.itamserveradmin.domain.assetRequest.dto.AstReqSearchForAdminReq;
import com.kbds.itamserveradmin.domain.assetRequest.dto.AstReqSearchForAdminRes;
import com.kbds.itamserveradmin.domain.assetRequest.dto.AstReqSearchReq;
import com.kbds.itamserveradmin.domain.assetRequest.dto.AstReqSearchRes;
import com.kbds.itamserveradmin.domain.assetRequest.entity.*;
import com.kbds.itamserveradmin.domain.contract.entity.QContract;
import com.kbds.itamserveradmin.domain.user.entity.QUser;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Page<AstReqSearchForAdminRes> searchForAdmin(AstReqSearchForAdminReq req, Pageable pageable) {

        List<AstReqSearchForAdminRes> contents = queryFactory
                .select(Projections.fields(AstReqSearchForAdminRes.class,
                        QAssetRequestManage.assetRequestManage.astReqName, //자산이름
                        QAssetRequestManage.assetRequestManage.astReqMgId,
                        QAssetRequestManage.assetRequestManage.assetRequest.astReqId, //요청PK
                        QAssetRequestManage.assetRequestManage.astReqMgDate, //요청날짜
                        QAssetRequestManage.assetRequestManage.astReqMgStatus, //요청관리 상리
                        QUser.user.userName, //요청자 이름
                        QUser.user.userId, //요청자 PK
                        QAssetRequestManage.assetRequestManage.astReqDept, //요청부서
                        QAssetRequest.assetRequest.contract.contId
                        ))
                .from(QAssetRequestManage.assetRequestManage)
                .join(QAssetRequestManage.assetRequestManage.assetRequest, QAssetRequest.assetRequest)
                .join(QAssetRequest.assetRequest.astRequestUser, QUser.user)
                .join(QAssetRequest.assetRequest.contract,QContract.contract)
                .where(eqAstRequestMangerUser(req.getUserId()) ,
                        findByReqUser(req.getUserName()),
                        findByReqDept(req.getAstReqDept()),
                        findByReqMgStatus(req.getAstReqMgStatus()),
                        UserRequestCond(req.getStart(),req.getEnd()))
                .orderBy(QAssetRequest.assetRequest.astReqMgDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        Long total = queryFactory
                .select(QAssetRequestManage.assetRequestManage.count())
                .from(QAssetRequestManage.assetRequestManage)
                .where(eqAstRequestMangerUser(req.getUserId()) ,
                        findByReqUser(req.getUserName()),
                        findByReqDept(req.getAstReqDept()),
                        findByReqMgStatus(req.getAstReqMgStatus()),
                        UserRequestCond(req.getStart(),req.getEnd()))
                .fetchOne();

        System.out.println("total>>"+total);




        return new PageImpl<>(contents,pageable,total);
    }

    private BooleanExpression eqAstRequestMangerUser(String userId){ // 요청관리 본인(메니저인사람)으로 검색

        return QAssetRequestManage.assetRequestManage.assetAdmin.astAdminId.eq(userId);

    }

    private BooleanExpression astRequestUser(String userId){ //요청자가 요청한거 검색

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
    private BooleanExpression findByReqUser(String userName){
        if(userName != null){
            return QAssetRequestManage.assetRequestManage.assetRequest.astRequestUser.userName.eq(userName); //이름 같은사람
        }

        return null;

    }

    private BooleanExpression findByReqDept(String dept){
        if(dept != null){
            return QAssetRequestManage.assetRequestManage.astReqDept.eq(dept);

        }

        return null;

    }

    private BooleanExpression findByReqMgStatus(String status){
        if(status != null){
            return QAssetRequestManage.assetRequestManage.astReqMgStatus.eq(RequestMangeStatus.valueOf(status));
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
