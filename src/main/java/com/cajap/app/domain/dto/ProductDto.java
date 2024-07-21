package com.cajap.app.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    private Long id;

    private String partNumber;

    private String name;

    private Double amount;

    private String unitMeasure;

    private String description;

    private String category;
}
