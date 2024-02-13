package com.kbds.itamserveradmin.domain.coperation.controller;

import com.kbds.itamserveradmin.domain.coperation.dto.RegisterCorporationDto;
import com.kbds.itamserveradmin.domain.coperation.service.CooperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kbitam-admin")
public class CooperationController {

    private final CooperationService cooperationService;
    private static final Logger logger = LoggerFactory.getLogger(CooperationController.class);


    @PostMapping(value = "/cooperation")
    public ResponseEntity<?> createCooperation(@RequestBody RegisterCorporationDto registerCorporationDto) {
        try {
            validateCooperationDto(registerCorporationDto);
            RegisterCorporationDto resultDto = cooperationService.saveCorp(
                    registerCorporationDto.getCorpName(),
                    registerCorporationDto.getCrn(),
                    registerCorporationDto.getCorpContact(),
                    registerCorporationDto.getCorpAddr(),
                    registerCorporationDto.getCorpUrl(),
                    registerCorporationDto.getCorpOwner(),
                    registerCorporationDto.getCorpRemarks(),
                    registerCorporationDto.isSubCorp()
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

    private void validateCooperationDto(RegisterCorporationDto registerCorporationDto) {
        if (org.springframework.util.StringUtils.isEmpty(registerCorporationDto.getCorpName())
                || org.springframework.util.StringUtils.isEmpty(registerCorporationDto.getCrn())
                || org.springframework.util.StringUtils.isEmpty(registerCorporationDto.getCorpContact())
                || org.springframework.util.StringUtils.isEmpty(registerCorporationDto.getCorpAddr())
                || org.springframework.util.StringUtils.isEmpty(registerCorporationDto.getCorpUrl())
                || org.springframework.util.StringUtils.isEmpty(registerCorporationDto.getCorpOwner())
                || org.springframework.util.StringUtils.isEmpty(registerCorporationDto.getCorpRemarks())) {
            throw new IllegalArgumentException("400 ERROR");
        }
    }
}