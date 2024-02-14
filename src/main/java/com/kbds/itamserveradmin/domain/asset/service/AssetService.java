package com.kbds.itamserveradmin.domain.asset.service;

import com.kbds.itamserveradmin.domain.asset.dto.AssetRes;
import com.kbds.itamserveradmin.domain.asset.dto.ManualLogRes;
import com.kbds.itamserveradmin.domain.asset.entity.Asset;
import com.kbds.itamserveradmin.domain.asset.entity.ManualLog;
import com.kbds.itamserveradmin.domain.asset.entity.QManualLog;
import com.kbds.itamserveradmin.domain.assetRequest.entity.AssetRequest;
import com.kbds.itamserveradmin.domain.assetRequest.service.AssetRequestService;
import com.kbds.itamserveradmin.domain.contract.service.ContractService;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.EntityManager;
import static com.kbds.itamserveradmin.domain.asset.entity.QManualLog.manualLog;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins ="*", allowedHeaders = "*")
@Service
@RequiredArgsConstructor
public class AssetService {

    private final ContractService contractService;
    private final  ManualLogService manualLogService;
    private final AssetRequestService assetRequestService;

    @Autowired
    private EntityManager em;

    @Transactional
    public AssetRes getInfo(String contId, String userId) {
        Asset asset = contractService.findAstIdByContId(contId);
        if (asset == null) {
            return null;
        }
        List<String> mnLogVersList = new JPAQueryFactory(em)
                .select(manualLog.mnLogVer)
                .from(manualLog)
                .fetch();

// 버전 문자열을 크기 비교하여 내림차순으로 정렬하고, 최대 3개를 선택
        mnLogVersList = mnLogVersList.stream()
                .limit(3)
                .collect(Collectors.toList());

        AssetRequest astReq = assetRequestService.findAssetRequestByUserIdAndContId(userId, contId);
        return AssetRes.assetInfo(asset, astReq, mnLogVersList);
    }

    @Transactional
    public ManualLogRes getInstallGuide(String contId) {
        Asset asset = contractService.findAstIdByContId(contId);
        if (asset == null) {
            return null;
        }
        ManualLog manualLog = manualLogService.findByAsset_AstId(asset.getAstId());
        if (manualLog == null){
            return null;
        }
        return ManualLogRes.installGuideRes(manualLog);
    }

}
