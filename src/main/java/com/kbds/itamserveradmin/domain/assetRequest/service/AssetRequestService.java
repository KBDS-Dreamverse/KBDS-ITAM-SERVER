package com.kbds.itamserveradmin.domain.assetRequest.service;


import com.kbds.itamserveradmin.domain.assetRequest.dto.AssetRequestReq;
import com.kbds.itamserveradmin.domain.assetRequest.dto.AssetRequestRes;
import com.kbds.itamserveradmin.domain.assetRequest.entity.AssetRequest;
import com.kbds.itamserveradmin.domain.assetRequest.entity.RequestStatus;
import com.kbds.itamserveradmin.domain.assetRequest.repository.AssetRequestRepository;
import com.kbds.itamserveradmin.domain.contract.entity.Contract;
import com.kbds.itamserveradmin.domain.contract.repository.ContractRepository;
import com.kbds.itamserveradmin.domain.user.entity.User;
import com.kbds.itamserveradmin.domain.user.repository.UserRepository;
import com.kbds.itamserveradmin.global.exception.BaseException;
import com.kbds.itamserveradmin.global.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AssetRequestService {

    private final AssetRequestRepository assetRequestRepository;
    private final UserRepository userRepository;
    private final ContractRepository contractRepository;



    @Autowired
    public AssetRequestService(AssetRequestRepository assetRequestRepository, UserRepository userRepository, ContractRepository contractRepository) {
        this.assetRequestRepository = assetRequestRepository;
        this.userRepository = userRepository;
        this.contractRepository = contractRepository;
    }



    public AssetRequestRes RequestAsset(AssetRequestReq req){
        System.out.println("hi");

        User user = userRepository.findById(req.getUserId()).orElseThrow(
                () -> new BaseException(ErrorCode.NOT_FIND_USER) );

        System.out.println("hello "+user.getUserId());

        Contract contract = contractRepository.findById(req.getContId()).orElseThrow(
                () -> new BaseException(ErrorCode.NOT_FIND_CONTRACT) );

        System.out.println("hello222 "+contract.getContId());

        AssetRequest astreq = AssetRequest.builder()
                .astReqId("1")
                .astReqReason(req.getAstReqReason())
                .astReqStartDate(req.getAstReqStartDate().atStartOfDay())
                .astReqEndDate(req.getAstReqEndDate().atStartOfDay())
                .astReqStatus(RequestStatus.APPROVAL_WAIT)
                .astRequestUser(user)
                .contract(contract)
                .build();

        AssetRequest n = assetRequestRepository.save(astreq);

        return AssetRequestRes.of(n.getAstReqId());

    }





}
