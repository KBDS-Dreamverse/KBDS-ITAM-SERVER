package com.kbds.itamserveradmin.domain.cooperation.service;

import com.kbds.itamserveradmin.domain.cooperation.dto.RegisterCooperationDto;
import com.kbds.itamserveradmin.domain.cooperation.dto.RequestCooperationDto;

import java.util.List;

public interface CooperationService {
    RegisterCooperationDto saveCorp(String corpName, String crn, String corpContact, String corpAddr,
                                    String corpUrl, String corpOwner, String corpRemarks, boolean isSubCorp);

    List<RequestCooperationDto> findAllCorp();

}
