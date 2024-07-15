package com.cajap.app.dao.impl;

import com.cajap.app.TestDataUtil;
import com.cajap.app.domain.Supplier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SupplierDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private SupplierDaoImpl underTest;

    @Test
    public void testThatCreateSupplierGeneratesCorrectSql() {
        Supplier supplier = TestDataUtil.createTestSupplier();

        underTest.create(supplier);

        verify(jdbcTemplate).update(
                eq("INSERT INTO cajap.suppliers (name, address) VALUES (?,?)"),
                eq("Andrew"),
                eq("Cabuyao")
        );
    }
}
