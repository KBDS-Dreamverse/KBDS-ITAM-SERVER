package com.kbds.itamserveradmin.domain.contract.controller;

import com.kbds.itamserveradmin.domain.contract.dto.CalKeyRes;
import com.kbds.itamserveradmin.domain.contract.dto.DashBoardRes;
import com.kbds.itamserveradmin.domain.contract.dto.PasswordReq;
import com.kbds.itamserveradmin.domain.contract.service.ContractService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import static com.kbds.itamserveradmin.global.exception.ErrorCode.PASSWORD_INCORRECT;
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
        DashBoardRes dashBoardRes =  contractService.createDashBoard(contId, userId);
        return ok(dashBoardRes);
    }

    @PostMapping("/kbitam/{dept}/{contId}/cal")
    public ResponseEntity<?> findCalKey(
            @PathVariable(name = "contId") String contId,
            @RequestBody PasswordReq pwReq,
            @RequestHeader String userId) {
        final String correctPw = "1234";

        if (correctPw.equals(pwReq.getPw())) {
            CalKeyRes calKey = contractService.getCalKey(userId, contId);
            return ResponseEntity.ok(calKey);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(PASSWORD_INCORRECT);
        }

    }
}
