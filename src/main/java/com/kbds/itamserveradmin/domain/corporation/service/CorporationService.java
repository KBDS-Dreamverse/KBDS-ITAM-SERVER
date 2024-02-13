package com.kbds.itamserveradmin.domain.corporation.service;

import com.kbds.itamserveradmin.domain.corporation.dto.RegisterCorporationDto;
import com.kbds.itamserveradmin.domain.corporation.dto.RequestCorporationDto;

import java.util.List;

public interface CorporationService {
    RegisterCorporationDto saveCorp(String corpName, String crn, String corpContact, String corpAddr,
                                    String corpUrl, String corpOwner, String corpRemarks, boolean isSubCorp);

    List<RequestCorporationDto> findAllCorp();

}
