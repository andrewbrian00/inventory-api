package com.cajap.app;

import com.cajap.app.domain.Product;
import com.cajap.app.domain.Supplier;

public class TestDataUtil {

    private TestDataUtil() {

    }

    public static Product createTestProduct() {
        return Product.builder()
                .id(1L)
                .partNumber("10001")
                .name("NSK Bearing")
                .amount(100.0d)
                .unitMeasure("inch")
                .description("NSK diameter 1")
                .category("Bearing")
                .build();
    }

    public static Product createTestProductA() {
        return Product.builder()
                .id(2L)
                .partNumber("10002")
                .name("NSK Bearing")
                .amount(120.0d)
                .unitMeasure("inch")
                .description("NSK diameter 2")
                .category("Bearing")
                .build();
    }

    public static Product createTestProductB() {
        return Product.builder()
                .id(3L)
                .partNumber("10003")
                .name("NSK Bearing")
                .amount(130.0d)
                .unitMeasure("inch")
                .description("NSK diameter 3")
                .category("Bearing")
                .build();
    }

    public static Product createTestProductC() {
        return Product.builder()
                .id(4L)
                .partNumber("10004")
                .name("NSK Bearing")
                .amount(140.0d)
                .unitMeasure("inch")
                .description("NSK diameter 4")
                .category("Bearing")
                .build();
    }

    public static Supplier createTestSupplier() {
        return Supplier.builder()
                .id(1L)
                .name("Andrew")
                .address("Cabuyao")
                .build();
    }
}
