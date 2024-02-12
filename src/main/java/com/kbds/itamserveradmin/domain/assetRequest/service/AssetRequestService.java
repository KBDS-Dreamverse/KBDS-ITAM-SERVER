package com.kbds.itamserveradmin.domain.assetRequest.service;


import com.kbds.itamserveradmin.domain.assetRequest.dto.*;
import com.kbds.itamserveradmin.domain.assetRequest.entity.*;
import com.kbds.itamserveradmin.domain.assetRequest.repository.AssetRequestLogRepository;
import com.kbds.itamserveradmin.domain.assetRequest.repository.AssetRequestManageLogRepository;
import com.kbds.itamserveradmin.domain.assetRequest.repository.AssetRequestManageRepository;
import com.kbds.itamserveradmin.domain.assetRequest.repository.AssetRequestRepository;
import com.kbds.itamserveradmin.domain.contract.entity.Contract;
import com.kbds.itamserveradmin.domain.contract.entity.OpStatus;
import com.kbds.itamserveradmin.domain.contract.repository.ContractRepository;
import com.kbds.itamserveradmin.domain.contract.service.ContractService;
import com.kbds.itamserveradmin.domain.user.dto.AssetAdminList;
import com.kbds.itamserveradmin.domain.user.dto.AssetAdminListRes;
import com.kbds.itamserveradmin.domain.user.entity.User;
import com.kbds.itamserveradmin.domain.user.repository.AssetAdminRepository;
import com.kbds.itamserveradmin.domain.user.service.UserService;
import com.kbds.itamserveradmin.global.exception.BaseException;
import com.kbds.itamserveradmin.global.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class AssetRequestService {

    private final AssetRequestManageRepository assetRequestManageRepository;
    private final AssetRequestRepository assetRequestRepository;
    private final ContractRepository contractRepository;
    private final ContractService contractService;
    private final UserService userService;
    private final AssetAdminRepository assetAdminRepository;
    private final AssetRequestLogRepository assetRequestLogRepository;
    private final AssetRequestManageLogRepository assetRequestManageLogRepository;



    @Autowired
    public AssetRequestService(AssetRequestManageRepository assetRequestManageRepository, AssetRequestRepository assetRequestRepository, ContractRepository contractRepository, ContractService contractService, UserService userService, AssetAdminRepository assetAdminRepository, AssetRequestLogRepository assetRequestLogRepository, AssetRequestManageLogRepository assetRequestManageLogRepository) {
        this.assetRequestManageRepository = assetRequestManageRepository;
        this.assetRequestRepository = assetRequestRepository;
        this.contractRepository = contractRepository;
        this.contractService = contractService;
        this.userService = userService;
        this.assetAdminRepository = assetAdminRepository;
        this.assetRequestLogRepository = assetRequestLogRepository;
        this.assetRequestManageLogRepository = assetRequestManageLogRepository;
    }

    /**
     * @note : asset(contId로 식별)을 관리하는 관리자 LIST 호출.
     * @param conId
     * @return count( 몇 명 ), data ( 관리자 정보 List )
     */
    public AssetAdminListRes getAssetAdminList(String conId){

        List<AssetAdminList> astadminList = (List<AssetAdminList>) assetAdminRepository.getAssetAdminList(conId);
//        for(AssetAdminList aa : astadminList){
//            System.out.println(aa.getDept_name());
//        }
        return AssetAdminListRes.of(astadminList.size(),astadminList);
    }


    /**
     * *
     * @param contId
     * @param req
     * @return
     */
    public AssetRequestRes RequestAsset(String dept, String contId,
                                        AssetRequestReq req){


        //사용자 있음?
        User user = userService.getUser(req.getUserId());

        //계약존재?
        Contract contract = contractService.getContract(contId);

        //운영중인 계약?
        if(contract.getContOpStatus() != OpStatus.IN_OPERATION ){
            throw new BaseException(ErrorCode.CONTRACT_IS_NOT_IN_OPERATION);
        }


        //운영중인 계약임. 신청 가능함.
        //신청 진행
        AssetRequest astReq = AssetRequest.builder()
                .astReqId(UUID.randomUUID().toString())
                .astReqReason(req.getAstReqReason())
                .astReqStartDate(req.getAstReqStartDate().atStartOfDay())
                .astReqEndDate(req.getAstReqEndDate().atStartOfDay())
                .astReqStatus(RequestStatus.APPROVAL_WAIT)
                .astRequestUser(user)
                .contract(contract)
                .build();

        AssetRequest astSaved = assetRequestRepository.save(astReq);

        //자산 요청 로그 저장
        saveAssetRequestLog(req.getAstName(),req.getAstVer(),astSaved,user);


        List<String> astReqMgIdList = new ArrayList<>();
        //신청 완료되면 자산 요청관리에 넣어줌.
        for(String astAdmin : req.getAssetAdminList()){

            AssetRequestManage astReqMg = AssetRequestManage.builder()
                    .astReqMgId(UUID.randomUUID().toString())
                    .astReqMgStatus(RequestMangeStatus.APPROVAL_WAIT)
                    .astReqDept(dept)
                    .astReqName(req.getAstName())
                    .assetAdmin(assetAdminRepository.getById(astAdmin))
                    .assetRequest(astSaved)
                    .build();

            AssetRequestManage savedAstMg = assetRequestManageRepository.save(astReqMg);
            astReqMgIdList.add(savedAstMg.getAstReqMgId());
            //자산 요청 관리 로그 저장
            saveAssetRequestMangeLog(savedAstMg);

        }





        return AssetRequestRes.of(astReq.getAstReqId(), astReqMgIdList);

    }



    public void saveAssetRequestLog(String astName, String reqver, AssetRequest req, User user){
        AssetRequestLog astLog = AssetRequestLog.builder()
                .astReqLogId(UUID.randomUUID().toString())
                .astReqLogStatus(req.getAstReqStatus())
                .astReqVer(reqver)
                .astReqName(astName)
                .astReqCnt(0L)
                .assetRequest(req)
                .assetRequestUser(user).build();


        try{
            assetRequestLogRepository.save(astLog);
        }catch (Exception e){
            throw new BaseException(ErrorCode.FAIL_SAVED_ASSETREQUESTLOG);
        }



    }


    public void saveAssetRequestMangeLog(AssetRequestManage savedAstMg){

        AssetRequestManageLog astReqMgLog= AssetRequestManageLog.builder()
                .astReqMgLogId(UUID.randomUUID().toString())
                .astReqMgLogStatus(savedAstMg.getAstReqMgStatus())
                .astReqName(savedAstMg.getAstReqName())
                .assetRequestManage(savedAstMg)
                .build();

        try{
            assetRequestManageLogRepository.save(astReqMgLog);
        }catch (Exception e){
            throw new BaseException(ErrorCode.FAIL_SAVED_ASSETREQUESTMGLOG);
        }


    }

    public Page<AstReqSearchRes> userRequestSearch(AstReqSearchReq req, Pageable pageable){
        System.out.println("====서비스 =====");
        Page<AstReqSearchRes> t= assetRequestRepository.search(req,pageable);
        return t;

    }
//
//    public AstReqDetailRes getAstReqDetatils(String astReqId, String userId){
//        System.out.println("zzzzz");
//        List<AssetRequestManage> e = assetRequestManageRepository.findByAstReqIdWithAssetRequest(astReqId);
//        System.out.println(e.size());
//        System.out.println(e.toString());
//        System.out.println("====1===");
//        List<AstReqAdminList> admins = e.stream()
//                        .map(o -> new AstReqAdminList(o))
//                                .collect(Collectors.toList());
//        System.out.println(admins.size());
//
//        System.out.println("====2===");
//
//
//
//
//
//        AssetRequest astreq = e.get(0).getAssetRequest();
//        String astReqName = e.get(0).getAstReqName();
//        String requestStatus = astreq.getAstReqStatus().getValue();
//        LocalDateTime astReqStartDate = astreq.getAstReqStartDate();
//        LocalDateTime astReqEndDate =astreq.getAstReqEndDate();
//        String astReqReason = astreq.getAstReqReason();
//
//        System.out.println("====3===");
//
//        User user = userService.getUser(userId);
//
//        System.out.println("====4===");
//
//
//
//
//
//        AstReqDetailRes res = AstReqDetailRes.of(astReqName,requestStatus,astReqStartDate,astReqEndDate,astReqReason,user,admins);
//
//        System.out.println("====5===");
//
//
//        return res;
//
//
//    }


    public AstReqDetailRes getAstReqDetatils(String astReqId, String userId){
        System.out.println("===1===");

        List<AssetRequestManage> e = assetRequestManageRepository.findByAstReqId(astReqId);
        System.out.println(e.size());
        System.out.println("===2===");

        List<AstReqAdminList> admins = e.stream()
                .map(o -> new AstReqAdminList(o))
                .collect(Collectors.toList());

        System.out.println(admins.size());
        System.out.println("===3===");




        AssetRequest astreq = e.get(0).getAssetRequest();
        System.out.println(astreq.getAstReqId());
        System.out.println("===4===");

        String astReqName = e.get(0).getAstReqName();

        System.out.println(astReqName);
        System.out.println("===4===");


        String requestStatus = astreq.getAstReqStatus().getValue();

        System.out.println(requestStatus);
        System.out.println("===5===");
        LocalDateTime astReqStartDate = astreq.getAstReqStartDate();
        LocalDateTime astReqEndDate =astreq.getAstReqEndDate();

        System.out.println(astReqStartDate);
        System.out.println("===6===");
        String astReqReason = astreq.getAstReqReason();

        System.out.println(astReqReason);
        System.out.println("===7===");
        User user = userService.getUser(userId);

        System.out.println(user.getUserName());
        System.out.println("===8-==");




        AstReqDetailRes res = AstReqDetailRes.of(astReqName,requestStatus,astReqStartDate,astReqEndDate,astReqReason,user,admins);



        return res;


    }


    public void updateUserRequestStatus(String astReqId){
        //변경 가능한지에 대한 유효성 검사

        //변경 가능하다면 변경 시작
        AssetRequest assetRequest = assetRequestRepository.findById(astReqId).orElseThrow(
                ()-> new BaseException(ErrorCode.INTERNAL_SERVER_ERROR)
        );

        if(assetRequest.getAstReqStatus() != RequestStatus.APPROVAL_WAIT){
            new BaseException(ErrorCode.INTERNAL_SERVER_ERROR); //변경불가
        }

        assetRequest.setAstReqStatus(RequestStatus.CANCEL);


    }

    public AstReqStatusUpdateRes saveLogUpdateUserRequestStatus(String astReqID, RequestStatus status){

        //로그 가져와서~
        AssetRequestLog recent = assetRequestLogRepository.getRecentLog(astReqID);

//        String PK = recent.getAstReqLogId().charAt(recent.getAstReqLogId().length() - 1)

        //002 하드 코딩 하면 안됨..PK 바꾸는 로직 추가하면 바뀔 예정..
        AssetRequestLog astLog = AssetRequestLog.builder()
                .astReqLogId(recent.getAstReqLogId()+"002")
                .astReqLogStatus(status)
                .astReqVer(recent.getAstReqVer())
                .astReqName(recent.getAstReqName())
                .astReqCnt(0L)
                .assetRequest(recent.getAssetRequest())
                .assetRequestUser(recent.getAssetRequestUser()).build();

        AssetRequestLog saved = null;

        try{
            saved = assetRequestLogRepository.save(astLog);
        }catch (Exception e){
            throw new BaseException(ErrorCode.FAIL_SAVED_ASSETREQUESTLOG);
        }


        return AstReqStatusUpdateRes.of(saved.getAstReqLogId(),astReqID, saved.getAstReqLogStatus());


    }





    public String test(String userId){

        assetRequestRepository.test(userId);
        return "zz";

    }




}
