package com.kbds.itamserveradmin.domain.assetRequest.service;


import com.kbds.itamserveradmin.domain.assetRequest.dto.AssetRequestReq;
import com.kbds.itamserveradmin.domain.assetRequest.dto.AssetRequestRes;
import com.kbds.itamserveradmin.domain.assetRequest.entity.AssetRequest;
import com.kbds.itamserveradmin.domain.assetRequest.entity.RequestStatus;
import com.kbds.itamserveradmin.domain.assetRequest.repository.AssetRequestRepository;
import com.kbds.itamserveradmin.domain.contract.entity.Contract;
import com.kbds.itamserveradmin.domain.contract.repository.ContractRepository;
import com.kbds.itamserveradmin.domain.contract.service.ContractService;
import com.kbds.itamserveradmin.domain.user.dto.AssetAdminList;
import com.kbds.itamserveradmin.domain.user.dto.AssetAdminListRes;
import com.kbds.itamserveradmin.domain.user.entity.User;
import com.kbds.itamserveradmin.domain.user.repository.AssetAdminRepository;
import com.kbds.itamserveradmin.domain.user.repository.UserRepository;
import com.kbds.itamserveradmin.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AssetRequestService {

    private final AssetRequestRepository assetRequestRepository;
    private final ContractRepository contractRepository;

    private final ContractService contractService;
    private final UserService userService;

    private final AssetAdminRepository assetAdminRepository;



    @Autowired
    public AssetRequestService(AssetRequestRepository assetRequestRepository, UserRepository userRepository, ContractRepository contractRepository, ContractService contractService, UserService userService, AssetAdminRepository assetAdminRepository) {
        this.assetRequestRepository = assetRequestRepository;
        this.contractRepository = contractRepository;
        this.contractService = contractService;
        this.userService = userService;
        this.assetAdminRepository = assetAdminRepository;
    }


    /**
     * *
     * @param conId
     * @note : asset(contId로 식별)을 관리하는 관리자 LIST 호출.
     * @return count( 몇 명 ), data ( 관리자 정보 List )
     */
    public AssetAdminListRes getAssetAdminList(String conId){

        List<AssetAdminList> astadminList = (List<AssetAdminList>) assetAdminRepository.getAssetAdminList(conId);
        for(AssetAdminList aa : astadminList){
            System.out.println(aa.getDept_name());
        }
        return AssetAdminListRes.of(astadminList.size(),astadminList);
    }





    public AssetRequestRes RequestAsset(String contId,
                                        AssetRequestReq req){


        //사용자 있음?
        User user = userService.getUser(req.getUserId());

        //계약존재?
        Contract contract = contractService.getContract(contId);


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
