package com.kbds.itamserveradmin.domain.cooperation.controller;

import com.kbds.itamserveradmin.domain.cooperation.dto.CooperationDto;
import com.kbds.itamserveradmin.domain.cooperation.service.CooperationService;
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
    public ResponseEntity<?> createCooperation(@RequestBody CooperationDto cooperationDto) {
        try {
            validateCooperationDto(cooperationDto);
            CooperationDto resultDto = cooperationService.saveCorp(
                    cooperationDto.getCorpName(),
                    cooperationDto.getCrn(),
                    cooperationDto.getCorpContact(),
                    cooperationDto.getCorpAddr(),
                    cooperationDto.getCorpUrl(),
                    cooperationDto.getCorpOwner(),
                    cooperationDto.getCorpRemarks(),
                    cooperationDto.isSubCorp()
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

    private void validateCooperationDto(CooperationDto cooperationDto) {
        if (org.springframework.util.StringUtils.isEmpty(cooperationDto.getCorpName())
                || org.springframework.util.StringUtils.isEmpty(cooperationDto.getCrn())
                || org.springframework.util.StringUtils.isEmpty(cooperationDto.getCorpContact())
                || org.springframework.util.StringUtils.isEmpty(cooperationDto.getCorpAddr())
                || org.springframework.util.StringUtils.isEmpty(cooperationDto.getCorpUrl())
                || org.springframework.util.StringUtils.isEmpty(cooperationDto.getCorpOwner())
                || org.springframework.util.StringUtils.isEmpty(cooperationDto.getCorpRemarks())) {
            throw new IllegalArgumentException("400 ERROR");
        }
    }
}