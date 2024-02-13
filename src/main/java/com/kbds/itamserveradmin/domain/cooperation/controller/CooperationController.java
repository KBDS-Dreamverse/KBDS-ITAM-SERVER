package com.kbds.itamserveradmin.domain.cooperation.controller;

import com.kbds.itamserveradmin.domain.cooperation.dto.RegisterCooperationDto;
import com.kbds.itamserveradmin.domain.cooperation.dto.RequestCooperationDto;
import com.kbds.itamserveradmin.domain.cooperation.service.CooperationService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kbitam-admin/cooperation")
public class CooperationController {

    private final CooperationService cooperationService;
    private static final Logger logger = LoggerFactory.getLogger(CooperationController.class);


    @PostMapping(value = "/registration")
    public ResponseEntity<?> createCooperation(@RequestBody RegisterCooperationDto registerCooperationDto) {
        try {
            validateCooperationDto(registerCooperationDto);
            RegisterCooperationDto resultDto = cooperationService.saveCorp(
                    registerCooperationDto.getCorpName(),
                    registerCooperationDto.getCrn(),
                    registerCooperationDto.getCorpContact(),
                    registerCooperationDto.getCorpAddr(),
                    registerCooperationDto.getCorpUrl(),
                    registerCooperationDto.getCorpOwner(),
                    registerCooperationDto.getCorpRemarks(),
                    registerCooperationDto.isSubCorp()
            );
            return ResponseEntity.ok(resultDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            String errorMessage = "500 ERROR";
            logger.error(errorMessage, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @GetMapping(value = "/cooperation-list")
    public ResponseEntity<?> findAllCooperation() {
        List<RequestCooperationDto> cooperationDtoList = cooperationService.findAllCorp();
        try {
            validateResponseCooperationDto(cooperationDtoList);
            return ResponseEntity.ok(cooperationDtoList);
        } catch (
                IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (
                Exception e) {
            String errorMessage = "500 ERROR";
            logger.error(errorMessage, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }


    private void validateCooperationDto(RegisterCooperationDto registerCooperationDto) {
        if (org.springframework.util.StringUtils.isEmpty(registerCooperationDto.getCorpName())
                || org.springframework.util.StringUtils.isEmpty(registerCooperationDto.getCrn())
                || org.springframework.util.StringUtils.isEmpty(registerCooperationDto.getCorpContact())
                || org.springframework.util.StringUtils.isEmpty(registerCooperationDto.getCorpAddr())
                || org.springframework.util.StringUtils.isEmpty(registerCooperationDto.getCorpUrl())
                || org.springframework.util.StringUtils.isEmpty(registerCooperationDto.getCorpOwner())
                || org.springframework.util.StringUtils.isEmpty(registerCooperationDto.getCorpRemarks())
                || org.springframework.util.StringUtils.isEmpty(registerCooperationDto.isSubCorp())) {
            throw new IllegalArgumentException("400 ERROR");
        }
    }

    private void validateResponseCooperationDto(List<RequestCooperationDto> cooperationDtoList) {
        for (RequestCooperationDto dto : cooperationDtoList) {
            if (org.springframework.util.StringUtils.isEmpty(dto.getCorpName())
                    || org.springframework.util.StringUtils.isEmpty(dto.getCrn())
                    || org.springframework.util.StringUtils.isEmpty(dto.getCorpContact())
                    || org.springframework.util.StringUtils.isEmpty(dto.getCorpAddr())
                    || org.springframework.util.StringUtils.isEmpty(dto.getCorpUrl())
                    || org.springframework.util.StringUtils.isEmpty(dto.getCorpOwner())
                    || org.springframework.util.StringUtils.isEmpty(dto.getCorpRemarks())
                    || org.springframework.util.StringUtils.isEmpty(dto.isSubCorp())) {
                throw new IllegalArgumentException("400 ERROR");
            }
        }

    }

}