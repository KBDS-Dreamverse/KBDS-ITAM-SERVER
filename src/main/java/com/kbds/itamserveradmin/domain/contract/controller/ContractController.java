package com.kbds.itamserveradmin.domain.contract.controller;

import com.kbds.itamserveradmin.domain.contract.dto.CalKeyRes;
import com.kbds.itamserveradmin.domain.contract.dto.ContExpireRes;
import com.kbds.itamserveradmin.domain.contract.dto.DashBoardRes;
import com.kbds.itamserveradmin.domain.contract.dto.PasswordReq;
import com.kbds.itamserveradmin.domain.contract.dto.request.NumberOfUsersReq;
import com.kbds.itamserveradmin.domain.contract.dto.request.PeriodLicenseReq;
import com.kbds.itamserveradmin.domain.contract.dto.request.SupplyLicense;
import com.kbds.itamserveradmin.domain.contract.dto.request.RegisterContractReq;
import com.kbds.itamserveradmin.domain.contract.service.ContractService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.kbds.itamserveradmin.global.exception.ErrorCode.*;
import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins ="*", allowedHeaders = "*")
public class ContractController {
    private final ContractService contractService;

    @GetMapping("/kbitam/{dept}/{contId}/dashboard")
    public ResponseEntity<?> getDashboard(
            @PathVariable String dept,
            @PathVariable String contId,
            @RequestHeader String userId) {
        try {
            DashBoardRes dashBoardRes = contractService.createDashBoard(contId, userId);
            return ok(dashBoardRes);
        } catch (IllegalArgumentException e) {
            // CONTRACT_NOT_FOUND 예외 처리
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PostMapping("/kbitam/{dept}/{contId}/cal")
    public ResponseEntity<?> findCalKey(
            @PathVariable(name = "contId") String contId,
            @RequestBody PasswordReq pwReq,
            @RequestHeader String userId) {

        final String correctPw = "1234";

        if (!correctPw.equals(pwReq.getPw())) {
            return ResponseEntity.ok(PASSWORD_INCORRECT);
        }

        CalKeyRes calKey = contractService.getCalKey(userId, contId);
        if (calKey == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(CONTRACT_IS_NOT_CAL_LIC);
        }
        return ResponseEntity.ok(calKey);
    }


    @GetMapping("/kbitam/{dept}/{contId}/expire")
    public ResponseEntity<?> expire(
            @PathVariable String dept,
            @PathVariable String contId,
            @RequestHeader String userId) {
        try {
            ContExpireRes contExpireRes = contractService.getExpireInfo(contId, userId);
            return ResponseEntity.ok(contExpireRes);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //    @PatchMapping("/kbitam/{dept}/{contId}/renewal")
//    public ResponseEntity<?> renewal(
//            @PathVariable String dept,
//            @PathVariable String contId) {
//
//    }
    @PatchMapping("/kbitam/{dept}/{contId}/stop")
    public ResponseEntity<?> stop(
            @PathVariable String dept,
            @PathVariable String contId,
            @RequestHeader String userId) {
        try {
            contractService.stopContract(contId, userId);
            return ResponseEntity.ok("Successfully stop the contract!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            // CONTRACT_IS_ALREADY_IN_DISPOSAL 예외 처리
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ASSET_IS_NOT_INUSE);
        }
    }

    // 계약 등록 (요청 없이)
    @PostMapping("/kbitam/")
    public ResponseEntity<?> registerContract(
            @RequestHeader String userId,
            @RequestBody @Valid RegisterContractReq registerContractReq
    )
    {
        contractService.registerContract(userId, registerContractReq);
        return ResponseEntity.ok("Successfully registered contract!");
    }

    // 라이센스 공급형태
    @PostMapping("/kbitam/supply/{contractId}")
    public ResponseEntity<?> registerSupplyLicense(
            @RequestHeader String userId,
            @PathVariable String contractId,
            @RequestBody @Valid SupplyLicense supplyLicense
    )
    {
        contractService.registerSupplyLicense(userId, contractId, supplyLicense);
        return ResponseEntity.ok("Successfully registered contract!");
    }

    // 라이센스 기간
    @PostMapping("/kbitam/period/{contractId}")
    public ResponseEntity<?> registerPeriodLicense(
            @RequestHeader String userId,
            @PathVariable String contractId,
            @RequestBody @Valid PeriodLicenseReq periodLicenseReq
    )
    {
        contractService.registerPeriodLicense(userId, contractId, periodLicenseReq);
        return ResponseEntity.ok("Successfully registered contract!");
    }

    // 라이센스 사용자 수
    @PostMapping("/kbitam/numbers/{contractId}")
    public ResponseEntity<?> registerNumberOfUsersLicense(
            @RequestHeader String userId,
            @PathVariable String contractId,
            @RequestBody @Valid NumberOfUsersReq numberOfUsersReq
    )
    {
        contractService.registerNumberOfUsersLicense(userId, contractId, numberOfUsersReq);
        return ResponseEntity.ok("Successfully registered contract!");
    }

    // 계약 등록 (요청 ID)
    @PostMapping("/kbitam/{newAssetRequestId}")
    public ResponseEntity<?> registerContractByRequest(
            @RequestHeader String userId,
            @PathVariable String newAssetRequestId,
            @RequestBody @Valid RegisterContractReq registerContractReq
    )
    {
        contractService.registerContractByRequest(userId, newAssetRequestId, registerContractReq);
        return ResponseEntity.ok("Successfully registered contract!");
    }

    // 계약대상 자산 조회
    @GetMapping("/kbitam/assets")
    public ResponseEntity<?> getAssets(

    )
    {
        return null;
    }

    // 계약업체 조회
    @GetMapping("/kbitam/corporations")
    public ResponseEntity<?> getCorporation(

    )
    {
        return null;
    }

    // 계약 조회
    @GetMapping("/kbitam")
    public ResponseEntity<?> getContract(

    )
    {
        return null;
    }

    // 계약 조회
    @PatchMapping("/kbitam/{contractId}")
    public ResponseEntity<?> updateContract(
            @PathVariable Long contractId
    )
    {

        return null;
    }
}

