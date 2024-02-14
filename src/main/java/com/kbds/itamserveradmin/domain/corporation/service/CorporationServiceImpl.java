package com.kbds.itamserveradmin.domain.corporation.service;

import com.kbds.itamserveradmin.domain.corporation.dto.RegisterCorporationDto;
import com.kbds.itamserveradmin.domain.corporation.dto.RequestCorporationDto;
import com.kbds.itamserveradmin.domain.corporation.entity.Corporation;
import com.kbds.itamserveradmin.domain.corporation.repository.CorporationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CorporationServiceImpl implements CorporationService {

    private final CorporationRepository corporationRepository;

    @Override
    public RegisterCorporationDto saveCorp(String corpName, String crn, String corpContact,
                                           String corpAddr, String corpUrl, String corpOwner,
                                           String corpRemarks, boolean isSubCorp) {
        // Id 생성 로직
//        corpId = Math.random();
        // 전화번호 4자리 +랜덤함수
        Corporation corporation = new Corporation(corpName, crn, corpContact, corpAddr,corpUrl, corpOwner, corpRemarks, isSubCorp);
        corporation = corporationRepository.save(corporation);
        return new RegisterCorporationDto(corporation.getCorpName(), corporation.getCrn(), corporation.getCorpContact(),
                corporation.getCorpAddr(), corporation.getCorpUrl(), corporation.getCorpOwner(), corporation.getCorpRemarks(), corporation.isSubCorp());
    }

    @Override
    @Transactional(readOnly = true) // 트랜젝션 조회기능 유지, 조회속도 향상
    public List<RequestCorporationDto> findAllCorp() {
        List<Corporation> corporationList = corporationRepository.findAll();
        return corporationList.stream()
                .map(this::convertDto).collect(Collectors.toList());
    }

    @Override
    public RequestCorporationDto findOneCorp(int id) {
        Corporation corporation = corporationRepository.findById(id).orElse(null);
        if (corporation != null) {
            return convertDto(corporation);
        }
        return null;
    }


    private RequestCorporationDto convertDto(Corporation entity) {
        RequestCorporationDto dto = new RequestCorporationDto();
        dto.setCorpId(entity.getCorpId());
        dto.setCrn(entity.getCrn());
        dto.setCorpAddr(entity.getCorpAddr());
        dto.setCorpName(entity.getCorpName());
        dto.setCorpOwner(entity.getCorpOwner());
        dto.setCorpContact(entity.getCorpContact());
        dto.setCorpUrl(entity.getCorpUrl());
        dto.setCorpRemarks(entity.getCorpRemarks());
        dto.setSubCorp(entity.isSubCorp());
        return dto;
    }





}
