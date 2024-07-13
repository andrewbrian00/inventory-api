package com.cajap.app;

import com.cajap.app.domain.Product;
import com.cajap.app.domain.Supplier;

public class TestDataUtil {

    public static Product createTestProductA() {
        return Product.builder()
                .id(1L)
                .partNumber("10002")
                .name("NSK Bearing")
                .amount(120.0)
                .unitMeasure("inch")
                .description("NSK diameter 2")
                .category("Bearing")
                .build();
    }

    public static Product createTestProductB() {
        return Product.builder()
                .id(2L)
                .partNumber("10003")
                .name("NSK Bearing")
                .amount(130.0)
                .unitMeasure("inch")
                .description("NSK diameter 3")
                .category("Bearing")
                .build();
    }

    public static Product createTestProductC() {
        return Product.builder()
                .id(3L)
                .partNumber("10004")
                .name("NSK Bearing")
                .amount(140.0)
                .unitMeasure("inch")
                .description("NSK diameter 4")
                .category("Bearing")
                .build();
    }

    public static Supplier createTestSupplier() {
        return Supplier.builder()
                .id(1L)
                .name("test name")
                .address("test addresss")
                .build();
    }
}
