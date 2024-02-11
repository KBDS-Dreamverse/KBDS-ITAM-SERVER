package com.kbds.itamserveradmin.domain.assetRequest.service;


import com.kbds.itamserveradmin.domain.assetRequest.entity.AssetRequest;
import com.kbds.itamserveradmin.domain.assetRequest.repository.AssetRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AssetRequestService {

    private final AssetRequestRepository astReqRepository;

    public String getAstReqIdByUserIdAndContId(String userId, String contId) {
        List<AssetRequest> astReqs = astReqRepository.findByAstRequestUserUserId(userId);
        // astReqs에서 contId 값과 같은 astReq 객체의 astReqId 값을 리턴 (결과는 한 개만 나온다고 가정함. 그래서 List<String>이 아님)
        return astReqs.stream()
                .filter(astReq -> astReq.getContract() != null && contId.equals(astReq.getContract().getContId()))
                .map(AssetRequest::getAstReqId)
                .findFirst()
                .orElse(null);
    }


}
