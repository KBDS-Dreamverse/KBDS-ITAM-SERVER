package com.kbds.itamserveradmin.domain.contract.service;

import com.kbds.itamserveradmin.domain.contract.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;

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


}
