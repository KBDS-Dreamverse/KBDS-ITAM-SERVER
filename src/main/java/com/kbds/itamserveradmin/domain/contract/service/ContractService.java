package com.kbds.itamserveradmin.domain.contract.service;

import com.kbds.itamserveradmin.domain.contract.dto.DashBoardRes;
import com.kbds.itamserveradmin.domain.contract.entity.Contract;
import com.kbds.itamserveradmin.domain.contract.entity.NumOfUsersType;
import com.kbds.itamserveradmin.domain.contract.entity.PeriodType;
import com.kbds.itamserveradmin.domain.contract.entity.SupplyType;
import com.kbds.itamserveradmin.domain.contract.repository.ContractRepository;
import com.kbds.itamserveradmin.domain.contract.repository.NumOfUsersTypeRepository;
import com.kbds.itamserveradmin.domain.contract.repository.PeriodTypeRepository;
import com.kbds.itamserveradmin.domain.contract.repository.SupplyTypeRepository;
import com.kbds.itamserveradmin.domain.purchaseRequest.entity.NewAssetRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.kbds.itamserveradmin.global.exception.ErrorCode.CONTRACT_NOT_FOUND;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;
    private final SupplyTypeRepository supplyTypeRepository;
    private final PeriodTypeRepository periodTypeRepository;
    private final NumOfUsersTypeRepository numOfUsersTypeRepository;


    public static List<String> parseContLicTag(String contLicTag) {
        // 분류별 의미를 저장하는 맵 정의
        Map<Character, String> supplyTypes = Map.of(
                '1', "패키지",
                '2', "Low Volume",
                '3', "High Volume",
                '4', "SaaS"
        );
        Map<Character, String> periodTypes = Map.of(
                '1', "구독",
                '2', "영구"
        );
        Map<Character, String> userTypes = Map.of(
                '1', "동시 사용자수",
                '2', "사이트",
                '3', "코어",
                '4', "1pc1copy",
                '5', "서버접속"
        );

        // contLicTag 파싱
        char supplyChar = contLicTag.charAt(0);
        char periodChar = contLicTag.charAt(1);
        char userChar = contLicTag.charAt(2);

        String supply = supplyTypes.getOrDefault(supplyChar, "공급형태 없음");
        String period = periodTypes.getOrDefault(periodChar, "기간 없음");
        String user = userTypes.getOrDefault(userChar, "사용자 수 없음");

        List<String> licenseTypes = new ArrayList<>();

        licenseTypes.add(supply);
        licenseTypes.add(period);
        licenseTypes.add(user);

        return licenseTypes;
    }

    public DashBoardRes createDashBoard(String contId) {
        // 1. 계약 id로 Contract 불러오기
        Contract findContract =  contractRepository.findById(contId)
                .orElseThrow(() -> new IllegalArgumentException(String.valueOf(CONTRACT_NOT_FOUND)));

        // 2. Contract.contLicTag 값 파싱
        List<String> licNames = parseContLicTag(findContract.getContLicTag());

        // 3. 라이선스 분류별 데이터 찾기(NumOfUsers, Period, Supply에서 각각 값 찾기)
        // contId로 요청Id 조회 -> userAssetReqInfo 테이블 가서 데이터 꺼내오기
        // 분류별로 따로 조회하기
        String licTag = findContract.getContLicTag();
        /*license 값 저장할 Hashmap*/
        HashMap<String, Object> licValues = new HashMap<>();

        // 3.1 공급형태
        SupplyType supplyType = supplyTypeRepository.findByCont_ContId(contId);
        if (licTag.charAt(0) == '1' || licTag.charAt(0) == '2') {   // 패키지, low volume
            licValues.put("splyVer", supplyType.getSplyVer());
        }
        if (licTag.charAt(0) == '4') {  // SaaS
            licValues.put("splyVer", supplyType.getSplyVer());
            licValues.put("acsUrl", supplyType.getAcsUrl());
        }

        // 3.2 기간 (1. yml에서 write-dates-as-timestamps: false 로 설정해도 날짜 리스트로 출력)
        // (2. PeriodType에서 @JsonFormat으로 설정해도 수정 안된다)
        PeriodType periodType = periodTypeRepository.findByCont_ContId(contId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedStartDate = periodType.getContStartDate().format(formatter);
        String formattedEndDate = periodType.getContEndDate().format(formatter);
        licValues.put("contStartDate", formattedStartDate);
        licValues.put("contEndDate", formattedEndDate);


        // 3.3 사용자
        NumOfUsersType numOfUsersType = numOfUsersTypeRepository.findByCont_ContId(contId);
        if (licTag.charAt(2) == '1') {   // 동시 사용자 수
            licValues.put("maxUsersLimit", numOfUsersType.getMaxUsersLimit());
            licValues.put("currUsers", numOfUsersType.getCurrUsers());
        }
        if (licTag.charAt(2) == '2') {   // 사이트
            licValues.put("ipRange", numOfUsersType.getIpRange());
        }
        if (licTag.charAt(2) == '3') {   // 코어
            licValues.put("maxCoreLimit", numOfUsersType.getMaxCoreLimit());
            licValues.put("currCore", numOfUsersType.getCurrCore());
        }



        // 4. 찾은 값들을 DashBoardRes에 담아 전달하기
        DashBoardRes dashBoardRes = DashBoardRes.builder()
                .contName(findContract.getContName())
                .licNames(licNames)
                .licValues(licValues)
                .build();

        return dashBoardRes;

    }



}
