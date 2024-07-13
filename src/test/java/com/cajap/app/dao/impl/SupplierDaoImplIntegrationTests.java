package com.cajap.app.dao.impl;

import com.cajap.app.TestDataUtil;
import com.cajap.app.domain.Supplier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SupplierDaoImplIntegrationTests {

    private final SupplierDaoImpl underTest;

    @Autowired
    public SupplierDaoImplIntegrationTests(SupplierDaoImpl underTest) {
        this.underTest = underTest;

    }

    @Test
    public void testThatSupplierCanBeCreatedAndRecalled() {
        Supplier supplier = TestDataUtil.createTestSupplier();
        underTest.create(supplier);
        Optional<Supplier> results = underTest.findOne(supplier.getId());
        assertThat(results).isPresent();
        assertThat(results.get()).isEqualTo(supplier);

    }
}