package com.kbds.itamserveradmin.domain.user.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Builder
@Getter
@AllArgsConstructor
@JsonPropertyOrder({"count", "data"})
public class AssetAdminListRes<T>   {
    private int count;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data; // 리스트의 값

    // 정적 팩토리 메서드
    public static <T> AssetAdminListRes<T> of(int count, T data) {
        return AssetAdminListRes.<T>builder()
                .count(count)
                .data(data)
                .build();
    }

}

