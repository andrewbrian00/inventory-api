package com.cajap.app;

import com.cajap.app.domain.dto.ProductDto;
import com.cajap.app.domain.entities.InventoryEntity;
import com.cajap.app.domain.entities.ProductEntity;
import com.cajap.app.domain.entities.SupplierEntity;

public class TestDataUtil {

    private TestDataUtil() {

    }

    public static ProductEntity createTestProduct() {
        return ProductEntity.builder()
                .id(1L)
                .partNumber("10001")
                .name("NSK Bearing")
                .amount(100.0d)
                .unitMeasure("inch")
                .description("NSK diameter 1")
                .category("Bearing")
                .build();
    }

    public static ProductEntity createTestProductA() {
        return ProductEntity.builder()
                .id(1L)
                .partNumber("10002")
                .name("NSK Bearing")
                .amount(120.0d)
                .unitMeasure("inch")
                .description("NSK diameter 2")
                .category("Bearing")
                .build();
    }

    public static ProductEntity createTestProductB() {
        return ProductEntity.builder()
                .partNumber("10003")
                .name("NSK Bearing")
                .amount(130.0d)
                .unitMeasure("inch")
                .description("NSK diameter 3")
                .category("Bearing")
                .build();
    }

    public static ProductEntity createTestProductC() {
        return ProductEntity.builder()
                .partNumber("10004")
                .name("NSK Bearing")
                .amount(140.0d)
                .unitMeasure("inch")
                .description("NSK diameter 4")
                .category("Bearing")
                .build();
    }

    public static ProductDto createTestProductDto() {
        return ProductDto.builder()
                .id(1L)
                .partNumber("10002")
                .name("NSK Bearing")
                .amount(120.0d)
                .unitMeasure("inch")
                .description("NSK diameter 2")
                .category("Bearing")
                .build();
    }

    public static SupplierEntity createTestSupplier() {
        return SupplierEntity.builder()
                .id(1L)
                .name("Andrew")
                .address("Cabuyao")
                .build();
    }

    public static InventoryEntity createTestInventory(ProductEntity productEntity, SupplierEntity supplierEntity) {
        return InventoryEntity.builder()
                .id(1L)
                .itemName("NSK Bearing")
                .itemCount(3)
                .product(productEntity)
                .supplier(supplierEntity)
                .build();
    }
}
