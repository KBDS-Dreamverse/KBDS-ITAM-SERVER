package com.kbds.itamserveradmin.domain.contract.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
@Builder
public class DashBoardRes {

    private String astName;

    /**
     * 라이선스 이름 리스트
     * */
    private List<String> licNames = new ArrayList<>();

    /**
     * 라이선스 값들 담겨 있는 Map
     */
    private HashMap<String, Object> licValues = new HashMap<>();




}