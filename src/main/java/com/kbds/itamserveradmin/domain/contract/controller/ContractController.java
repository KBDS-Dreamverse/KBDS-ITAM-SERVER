package com.kbds.itamserveradmin.domain.contract.controller;

import com.kbds.itamserveradmin.domain.contract.dto.DashBoardRes;
import com.kbds.itamserveradmin.domain.contract.service.ContractService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ContractController {
    private ContractService contractService;

//    @GetMapping("/kbitam/{dept}/{contId}/dashboard")
//    public ResponseEntity<>getDashboard(@PathVariable String dept, @PathVariable String contId) {
//        DashBoardRes dashBoardRes =  contractService.createDashBoard(contId);
//    }
}
