package com.kbds.itamserveradmin.domain.asset.service;

import com.kbds.itamserveradmin.domain.asset.entity.Asset;
import com.kbds.itamserveradmin.domain.asset.entity.AstTag;
import com.kbds.itamserveradmin.domain.asset.repository.AssetRepository;
import com.kbds.itamserveradmin.domain.assetRequest.entity.AssetRequest;
import com.kbds.itamserveradmin.domain.assetRequest.service.AssetRequestService;
import com.kbds.itamserveradmin.domain.corporation.entity.CorpPK;
import com.kbds.itamserveradmin.domain.corporation.entity.Corporation;
import com.kbds.itamserveradmin.domain.corporation.repository.CorporationRepository;
import com.kbds.itamserveradmin.domain.user.entity.User;
import com.kbds.itamserveradmin.domain.user.service.UserService;
import com.kbds.itamserveradmin.global.exception.BaseException;
import com.kbds.itamserveradmin.global.exception.ErrorCode;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.kbds.itamserveradmin.domain.asset.dto.AssetRes;
import com.kbds.itamserveradmin.domain.asset.dto.ManualLogRes;
import com.kbds.itamserveradmin.domain.asset.entity.Asset;
import com.kbds.itamserveradmin.domain.asset.entity.ManualLog;
import com.kbds.itamserveradmin.domain.contract.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.kbds.itamserveradmin.domain.asset.entity.QManualLog.manualLog;

@Service
@RequiredArgsConstructor
public class AssetService {
    private final AssetRepository assetRepository;
    private final CorporationRepository corporationRepository;
    private final UserService userService;
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

    public String createPK(String corp){


        //List<String> t = assetRepository.findCorpCntById(corp);
        String t = assetRepository.findCorpCntById(corp);
        System.out.println("<<<"+t);


        //int temp  = 0;
        if(t == null || t.toString() == "0"){ //회사만 있고(회사가 없을 수 없음) 자산이 등록되어 있지 않는 경우.
            System.out.println("nullllllll");
        }

        System.out.println(">>>> " + t.toString());


        int temp = Integer.parseInt(t.toString().substring(6,9)) + 1;

        String PK_NUM =t.toString().substring(0,6)+ String.format("%03d", temp);

        System.out.println(">>>>22"+PK_NUM);

        return PK_NUM;

    }

    public String testSaveForcreatePk(String corp){

        Corporation c = corporationRepository.findById(corp).orElseThrow(
                () -> new BaseException(ErrorCode.INTERNAL_SERVER_ERROR)
        );

        User user = userService.getUser("L00");

        System.out.println("들어가기전");
        Asset ast = Asset.builder()
                .astId(createPK(c.getCorpId()))
                .astName("zzzz")
                .astTag(AstTag.BANK)
                .corp(c)
                .user(user)
                .build();

        System.out.println("저장준비");
        Asset saved = assetRepository.save(ast);

        System.out.println("저장완료");

        //System.out.println(saved);

        String ttemp =saved.getAstId();
        System.out.println(ttemp);
        return ttemp;

    }

}
