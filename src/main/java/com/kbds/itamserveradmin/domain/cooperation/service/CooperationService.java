package com.kbds.itamserveradmin.domain.cooperation.service;

import com.kbds.itamserveradmin.domain.cooperation.dto.CooperationDto;

public interface CooperationService {
    CooperationDto saveCorp(String corpName, String crn, String corpContact, String corpAddr,
                            String corpUrl, String corpOwner, String corpRemarks, boolean isSubCorp);

}
