package com.kbds.itamserveradmin.domain.contract.service;

import com.kbds.itamserveradmin.domain.asset.entity.Asset;
import com.kbds.itamserveradmin.domain.asset.repository.AssetRepository;
import com.kbds.itamserveradmin.domain.assetRequest.entity.AssetRequest;
import com.kbds.itamserveradmin.domain.assetRequest.entity.RequestStatus;
import com.kbds.itamserveradmin.domain.assetRequest.entity.UserAssetRequestInfo;
import com.kbds.itamserveradmin.domain.assetRequest.repository.UserAssetRequestInfoRepository;
import com.kbds.itamserveradmin.domain.assetRequest.service.AssetRequestService;
import com.kbds.itamserveradmin.domain.contract.dto.CalKeyRes;
import com.kbds.itamserveradmin.domain.contract.dto.ContExpireRes;
import com.kbds.itamserveradmin.domain.contract.dto.DashBoardRes;
import com.kbds.itamserveradmin.domain.contract.dto.request.NumberOfUsersReq;
import com.kbds.itamserveradmin.domain.contract.dto.request.PeriodLicenseReq;
import com.kbds.itamserveradmin.domain.contract.dto.request.RegisterContractReq;
import com.kbds.itamserveradmin.domain.contract.dto.request.SupplyLicense;
import com.kbds.itamserveradmin.domain.contract.entity.*;
import com.kbds.itamserveradmin.domain.contract.repository.ContractRepository;
import com.kbds.itamserveradmin.domain.contract.repository.NumOfUsersTypeRepository;
import com.kbds.itamserveradmin.domain.contract.repository.PeriodTypeRepository;
import com.kbds.itamserveradmin.domain.contract.repository.SupplyTypeRepository;
import com.kbds.itamserveradmin.domain.corporation.entity.Corporation;
import com.kbds.itamserveradmin.domain.corporation.repository.CorporationRepository;
import com.kbds.itamserveradmin.domain.purchaseRequest.entity.NewAssetRequest;
import com.kbds.itamserveradmin.domain.purchaseRequest.repository.NewAssetRequestRepository;
import com.kbds.itamserveradmin.domain.user.entity.User;
import com.kbds.itamserveradmin.domain.user.repository.UserRepository;
import com.kbds.itamserveradmin.global.exception.BaseException;
import com.kbds.itamserveradmin.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static com.kbds.itamserveradmin.global.exception.ErrorCode.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ContractService {

    private final UserRepository userRepository;
    private final AssetRepository assetRepository;
    private final CorporationRepository corporationRepository;
    private final ContractRepository contractRepository;
    private final SupplyTypeRepository supplyTypeRepository;
    private final PeriodTypeRepository periodTypeRepository;
    private final NumOfUsersTypeRepository numOfUsersTypeRepository;
    private final UserAssetRequestInfoRepository userAssetRequestInfoRepository;
    private final NewAssetRequestRepository newAssetRequestRepository;

    private final AssetRequestService assetRequestService;


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
    //Ast id 찾는 메서드
    public Asset getAstIdByContId(String contId){
        Contract contract =  contractRepository.findById(contId)
                .orElseThrow(() -> new IllegalArgumentException(String.valueOf(CONTRACT_NOT_FOUND)));
        if (contract == null){
            return null;
        }
        return contract.getAst();
    }
    /**
     * DashBoard에 보여줄 데이터 가져오는 메서드
     * @param contId
     * @param userId
     * @return
     */
    public DashBoardRes createDashBoard(String contId, String userId) {
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
        List<LocalDateTime> dateTimeList = getPeriod(contId);
        List<String> formattedDate = getFormattedDate(dateTimeList);
        licValues.put("contStartDate", formattedDate.get(0));
        licValues.put("contEndDate", formattedDate.get(1));


        // 3.3 사용자
        NumOfUsersType numOfUsersType = numOfUsersTypeRepository.findByCont_ContId(contId);
        if (licTag.charAt(2) == '1') {   // 동시 사용자 수
            licValues.put("maxUsersLimit", numOfUsersType.getMaxUsersLimit());
            licValues.put("currUsers", numOfUsersType.getCurrUsers());
        }
        if (licTag.charAt(2) == '3') {   // 코어
            licValues.put("maxCoreLimit", numOfUsersType.getMaxCoreLimit());
            licValues.put("currCore", numOfUsersType.getCurrCore());
        }

        String astReqId = assetRequestService.getAstReqIdByUserIdAndContId(userId, contId);
        System.out.println("[asset_request_id]" + astReqId);
        UserAssetRequestInfo userAstReqInfo = userAssetRequestInfoRepository.findByAssetRequest_AstReqId(astReqId);
        if (licTag.charAt(2) == '2') {   // 사이트
            licValues.put("ipRange", numOfUsersType.getIpRange());
            licValues.put("userIp", userAstReqInfo.getUserIp());
            String ipRange = numOfUsersType.getIpRange().substring(0, 9);   //"192.168.3.0/24"일 때 "192.168.3" -> 이 값만 추출
            String userIp = userAstReqInfo.getUserIp().substring(0, 9);
            if (ipRange.equals(userIp)) {
                licValues.put("isAccessible", true);
            } else {
                licValues.put("isAccessible", false);
            }
            licValues.put("userPort", userAstReqInfo.getUserPort());

        }
        if (licTag.charAt(2) == '4') {   // 1PC1COPY
            licValues.put("licenseKey", userAstReqInfo.getLicenseKey().getLicKeyId());
        }
        if (licTag.charAt(2) == '5') {   // 서버접속(cal)
            // get 요청 시 cal key 주면 안된다.
        }



        // 4. 찾은 값들을 DashBoardRes에 담아 전달하기

        return DashBoardRes.builder()
                .contName(findContract.getContName())
                .licNames(licNames)
                .licValues(licValues)
                .build();

    }

    public CalKeyRes getCalKey(String userId, String contId) {
        Contract findContract =  contractRepository.findById(contId)
                .orElseThrow(() -> new IllegalArgumentException(String.valueOf(CONTRACT_NOT_FOUND)));

        // 해당 계약의 라이선스 조합이 서버접속이 아닌 경우 -> Exception 던지기
        if (findContract.getContLicTag().charAt(2) != '5') {
            return null;
        }
        String astReqId = assetRequestService.getAstReqIdByUserIdAndContId(userId, contId);
        UserAssetRequestInfo userAstReqInfo = userAssetRequestInfoRepository.findByAssetRequest_AstReqId(astReqId);

        String status = "";
        if (KeyStatus.IN_USE.equals(userAstReqInfo.getCalKey().getCalKeyStatus())) {
            status = "IN_USE";
        } else {
            status = "NOT_IN_USE";
        }

        return CalKeyRes.builder()
                .calKey(userAstReqInfo.getCalKey().getCalId())
                .calKeyStatus(status)
                .build();
    }

    public List<LocalDateTime> getPeriod(String contId) {
        List<LocalDateTime> period = new ArrayList<>(2);
        PeriodType periodType = periodTypeRepository.findByCont_ContId(contId);
        period.add(periodType.getContStartDate());
        period.add(periodType.getContEndDate());
        return period;
    }

    public List<LocalDateTime> getAstReqPeriod(String contId, String userId) {
        List<LocalDateTime> period = new ArrayList<>(2);
        AssetRequest assetRequest = assetRequestService.findAssetRequestByUserIdAndContId(userId, contId);
        period.add(assetRequest.getAstReqStartDate());
        period.add(assetRequest.getAstReqEndDate());
        return period;
    }

    public List<String> getFormattedDate(List<LocalDateTime> dateTimeList) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedStartDate = dateTimeList.get(0).format(formatter);
        String formattedEndDate = dateTimeList.get(1).format(formatter);

        List<String> formatted = new ArrayList<>();
        formatted.add(formattedStartDate);
        formatted.add(formattedEndDate);
        return formatted;
    }

    public ContExpireRes getExpireInfo(String contId, String userId) {
        AssetRequest assetRequest = assetRequestService.findAssetRequestByUserIdAndContId(userId, contId);
        if (assetRequest.getAstReqStatus() != RequestStatus.IN_USE) {
            if (assetRequest.getAstReqStatus() == RequestStatus.EXPIRED) {
                throw new IllegalStateException(String.valueOf(ASSET_IS_EXPIRE));
            }
            throw new IllegalStateException(String.valueOf(ASSET_IS_NOT_INUSE));
        }



        List<LocalDateTime> period = getAstReqPeriod(contId, userId);
        List<String> formattedDate = getFormattedDate(period);

        Long between =  ChronoUnit.DAYS.between(LocalDateTime.now(), period.get(1));

        return ContExpireRes.builder()
                .contStartDate(formattedDate.get(0))
                .contEndDate(formattedDate.get(1))
                .remainingDays(between)
                .build();
    }

    public void stopContract(String contId, String userId) {
        AssetRequest assetRequest = assetRequestService.findAssetRequestByUserIdAndContId(userId, contId);

        if (assetRequest.getAstReqStatus() == RequestStatus.IN_USE) {
            assetRequest.setAstReqStatus(RequestStatus.EXPIRED);
        } else {
            throw new IllegalStateException(String.valueOf(ASSET_IS_NOT_INUSE));
        }
    }

    @Transactional
    public Contract registerContract(String userId, RegisterContractReq registerContractReq) {
        User user = userRepository.findById(userId).orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));
        Asset asset = assetRepository.findById(registerContractReq.getAssetId()).orElseThrow(() -> new BaseException(ErrorCode.ASSET_NOT_FOUND));
        Corporation corporation = corporationRepository.findById(registerContractReq.getCorporationId()).orElseThrow(() -> new BaseException(CORPORATION_NOT_FOUND));
        return contractRepository.save(Contract.toEntity(registerContractReq, asset, corporation, user));
    }

    @Transactional
    public void registerSupplyLicense(String userId, String contractId, SupplyLicense supplyLicense) {
        User user = userRepository.findById(userId).orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));
        Contract contract = contractRepository.findById(contractId).orElseThrow(() -> new BaseException(CONTRACT_NOT_FOUND));
        supplyTypeRepository.save(SupplyType.toEntity(contract, supplyLicense));
    }

    @Transactional
    public void registerPeriodLicense(String userId, String contractId, PeriodLicenseReq periodLicenseReq) {
        User user = userRepository.findById(userId).orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));
        Contract contract = contractRepository.findById(contractId).orElseThrow(() -> new BaseException(CONTRACT_NOT_FOUND));
        periodTypeRepository.save(PeriodType.toEntity(contract, periodLicenseReq));
    }

    @Transactional
    public void registerNumberOfUsersLicense(String userId, String contractId, NumberOfUsersReq numberOfUsersReq) {
        User user = userRepository.findById(userId).orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));
        Contract contract = contractRepository.findById(contractId).orElseThrow(() -> new BaseException(CONTRACT_NOT_FOUND));
        numOfUsersTypeRepository.save(NumOfUsersType.toEntity(contract, numberOfUsersReq));
    }

    @Transactional
    public void registerContractByRequest(String userId, String newAssetRequestId, RegisterContractReq registerContractReq) {
        Contract contract = registerContract(userId, registerContractReq);
        NewAssetRequest newAssetRequest = newAssetRequestRepository.findById(newAssetRequestId).orElseThrow(() -> new BaseException(NEW_ASSET_REQUEST_NOT_FOUND));
        contract.toUpdateNewAssetRequest(newAssetRequest);
    }
}
