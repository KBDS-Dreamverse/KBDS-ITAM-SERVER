package com.kbds.itamserveradmin.domain.contract.dto;

import com.kbds.itamserveradmin.domain.contract.entity.OpStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StopContRes {
    private OpStatus opStatus;
}
