package com.kbds.itamserveradmin.domain.corporation.controller;

import com.kbds.itamserveradmin.domain.corporation.dto.RegisterCorporationDto;
import com.kbds.itamserveradmin.domain.corporation.dto.RequestCorporationDto;
import com.kbds.itamserveradmin.domain.corporation.service.CorporationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import static org.springframework.util.StringUtils.isEmpty;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kbitam-admin/corporation")
public class CorporationController {

    private final CorporationService corporationService;
    private static final Logger logger = LoggerFactory.getLogger(CorporationController.class);


    @PostMapping(value = "/registration")
    public ResponseEntity<?> createCorporation(@RequestBody RegisterCorporationDto registerCorporationDto) {
        try {
            validateCorporationDto(registerCorporationDto);
            RegisterCorporationDto resultDto = corporationService.saveCorp(
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

    @GetMapping(value = "/corporation-list")
    public ResponseEntity<?> findAllCorporation() {
        List<RequestCorporationDto> corporationDtoList = corporationService.findAllCorp();
        try {
            validateResponseCorporationDto(corporationDtoList);
            return ResponseEntity.ok(corporationDtoList);
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


    private void validateCorporationDto(RegisterCorporationDto registerCorporationDto) {
        if (isEmpty(registerCorporationDto.getCorpName())
                || isEmpty(registerCorporationDto.getCrn())
                || isEmpty(registerCorporationDto.getCorpContact())
                || isEmpty(registerCorporationDto.getCorpAddr())
                || isEmpty(registerCorporationDto.getCorpUrl())
                || isEmpty(registerCorporationDto.getCorpOwner())
                || isEmpty(registerCorporationDto.getCorpRemarks())
                || isEmpty(registerCorporationDto.isSubCorp())) {
            throw new IllegalArgumentException("400 ERROR");
        }
    }

    private void validateResponseCorporationDto(List<RequestCorporationDto> cooperationDtoList) {
        for (RequestCorporationDto dto : cooperationDtoList) {
            if (isEmpty(dto.getCorpId())
                    || isEmpty(dto.getCorpName())
                    || isEmpty(dto.getCrn())
                    || isEmpty(dto.getCorpContact())
                    || isEmpty(dto.getCorpAddr())
                    || isEmpty(dto.getCorpUrl())
                    || isEmpty(dto.getCorpOwner())
                    || isEmpty(dto.getCorpRemarks())
                    || isEmpty(dto.isSubCorp())) {
                throw new IllegalArgumentException("400 ERROR");
            }
        }

    }

}