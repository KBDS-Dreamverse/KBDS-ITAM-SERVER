package com.kbds.itamserveradmin.domain.contract.controller;

import com.kbds.itamserveradmin.domain.contract.dto.CalKeyRes;
import com.kbds.itamserveradmin.domain.contract.dto.ContExpireRes;
import com.kbds.itamserveradmin.domain.contract.dto.DashBoardRes;
import com.kbds.itamserveradmin.domain.contract.dto.PasswordReq;
import com.kbds.itamserveradmin.domain.contract.service.ContractService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.kbds.itamserveradmin.global.exception.ErrorCode.*;
import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ContractController {
    private final ContractService contractService;

    @GetMapping("/kbitam/{dept}/{contId}/dashboard")
    public ResponseEntity<DashBoardRes> getDashboard(
            @PathVariable String dept,
            @PathVariable String contId,
            @RequestHeader String userId) {
        try {
            DashBoardRes dashBoardRes = contractService.createDashBoard(contId, userId);
            return ok(dashBoardRes);
        } catch (IllegalArgumentException e) {
            // CONTRACT_NOT_FOUND 예외 처리
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/kbitam/{dept}/{contId}/cal")
    public ResponseEntity<?> findCalKey(
            @PathVariable(name = "contId") String contId,
            @RequestBody PasswordReq pwReq,
            @RequestHeader String userId) {

        final String correctPw = "1234";

        if (!correctPw.equals(pwReq.getPw())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(PASSWORD_INCORRECT);
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
            ContExpireRes contExpireRes = contractService.getExpire(contId, userId);
            return ResponseEntity.ok(contExpireRes);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ASSET_IS_NOT_INUSE);
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


}
