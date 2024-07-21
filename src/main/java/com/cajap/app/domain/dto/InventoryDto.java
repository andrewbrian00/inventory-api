package com.cajap.app.domain.dto;

import com.cajap.app.domain.entities.ProductEntity;
import com.cajap.app.domain.entities.SupplierEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryDto {

    private Long id;

    private String itemCode;

    private String itemName;

    private Integer itemCount;

    private ProductEntity product;

    private SupplierEntity supplier;
}
