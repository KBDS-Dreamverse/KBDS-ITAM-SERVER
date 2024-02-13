package com.kbds.itamserveradmin.domain.cooperation.service;

import com.kbds.itamserveradmin.domain.cooperation.dto.RegisterCooperationDto;
import com.kbds.itamserveradmin.domain.cooperation.dto.RequestCooperationDto;
import com.kbds.itamserveradmin.domain.cooperation.entity.Cooperation;
import com.kbds.itamserveradmin.domain.cooperation.repository.CooperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CooperationServiceImpl implements CooperationService{

    private final CooperationRepository cooperationRepository;

    @Override
    public RegisterCooperationDto saveCorp(String corpName, String crn, String corpContact,
                                           String corpAddr, String corpUrl, String corpOwner,
                                           String corpRemarks, boolean isSubCorp) {
        // Id 생성 로직
//        corpId = Math.random();
        // 전화번호 4자리 +랜덤함수
        Cooperation cooperation = new Cooperation(corpName, crn, corpContact, corpAddr,corpUrl, corpOwner, corpRemarks, isSubCorp);
        cooperation = cooperationRepository.save(cooperation);
        return new RegisterCooperationDto(cooperation.getCorpName(), cooperation.getCrn(), cooperation.getCorpContact(),
                cooperation.getCorpAddr(), cooperation.getCorpUrl(), cooperation.getCorpOwner(), cooperation.getCorpRemarks(), cooperation.isSubCorp());
    }

    @Override
    @Transactional(readOnly = true) // 트랜젝션 조회기능 유지, 조회속도 향상
    public List<RequestCooperationDto> findAllCorp() {
        List<Cooperation> cooperationList = cooperationRepository.findAll();
        return cooperationList.stream()
                .map(cooperation -> {
                    RequestCooperationDto dto = new RequestCooperationDto(
                            cooperation.getCorpName(), cooperation.getCrn(), cooperation.getCorpContact(),
                            cooperation.getCorpAddr(), cooperation.getCorpUrl(), cooperation.getCorpOwner(),
                            cooperation.getCorpRemarks(), cooperation.isSubCorp());
                    return dto;
                }).collect(Collectors.toList());
    }
}
