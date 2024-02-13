package com.kbds.itamserveradmin.domain.coperation.service;

import com.kbds.itamserveradmin.domain.coperation.dto.RegisterCorporationDto;
import com.kbds.itamserveradmin.domain.coperation.entity.Cooperation;
import com.kbds.itamserveradmin.domain.coperation.repository.CooperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CooperationServiceImpl implements CooperationService{

    private final CooperationRepository cooperationRepository;

    @Override
    public RegisterCorporationDto saveCorp(String corpName, String crn, String corpContact,
                                           String corpAddr, String corpUrl, String corpOwner,
                                           String corpRemarks, boolean isSubCorp) {
        // Id 생성 로직
//        corpId = Math.random();
        // 전화번호 4자리 +랜덤함수
        Cooperation cooperation = new Cooperation(corpName, crn, corpContact, corpAddr,corpUrl, corpOwner, corpRemarks, isSubCorp);
        cooperation = cooperationRepository.save(cooperation);
        return new RegisterCorporationDto(cooperation.getCorpName(), cooperation.getCrn(), cooperation.getCorpContact(),
                cooperation.getCorpAddr(), cooperation.getCorpUrl(), cooperation.getCorpOwner(), cooperation.getCorpRemarks(), cooperation.isSubCorp());
    }
}
