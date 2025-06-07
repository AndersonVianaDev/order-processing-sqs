package com.anderson.product_api.controller.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;

import java.util.List;

public record PageResponseDTO<T>(
        @JsonProperty("items") List<T> content,
        int page,
        int size,
        long totalPage,
        boolean hasNext
) {

    public static <T> PageResponseDTO<T> of (Page<T> page) {
        return new PageResponseDTO<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.hasNext()
        );
    }
}
