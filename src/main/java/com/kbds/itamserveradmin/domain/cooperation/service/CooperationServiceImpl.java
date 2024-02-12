package com.kbds.itamserveradmin.domain.cooperation.service;

import com.kbds.itamserveradmin.domain.cooperation.dto.CooperationDto;
import com.kbds.itamserveradmin.domain.cooperation.entity.Cooperation;
import com.kbds.itamserveradmin.domain.cooperation.repository.CooperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CooperationServiceImpl implements CooperationService{

    private final CooperationRepository cooperationRepository;

    @Override
    public CooperationDto saveCorp(String corpName, String crn, String corpContact,
                                   String corpAddr, String corpUrl, String corpOwner,
                                   String corpRemarks, boolean isSubCorp) {
        // Id 생성 로직
//        corpId = Math.random();
        // 전화번호 4자리 +랜덤함수
        Cooperation cooperation = new Cooperation(corpName, crn, corpContact, corpAddr,corpUrl, corpOwner, corpRemarks, isSubCorp);
        cooperation = cooperationRepository.save(cooperation);
        return new CooperationDto(cooperation.getCorpName(), cooperation.getCrn(), cooperation.getCorpContact(),
                cooperation.getCorpAddr(), cooperation.getCorpUrl(), cooperation.getCorpOwner(), cooperation.getCorpRemarks(), cooperation.isSubCorp());
    }
}
