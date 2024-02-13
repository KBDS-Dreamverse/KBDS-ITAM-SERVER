package com.kbds.itamserveradmin.domain.coperation.service;

import com.kbds.itamserveradmin.domain.coperation.dto.RegisterCorporationDto;

public interface CooperationService {
    RegisterCorporationDto saveCorp(String corpName, String crn, String corpContact, String corpAddr,
                                    String corpUrl, String corpOwner, String corpRemarks, boolean isSubCorp);

}
