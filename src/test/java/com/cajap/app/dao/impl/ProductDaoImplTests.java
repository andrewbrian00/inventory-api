package com.cajap.app.dao.impl;

import com.cajap.app.TestDataUtil;
import com.cajap.app.domain.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private ProductDaoImpl underTest;

    @Test
    public void testThatCreateProductGeneratesCorrectSql() {
        Product product = TestDataUtil.createTestProductA();
        underTest.create(product);

        verify(jdbcTemplate).update(
                eq("INSERT INTO products " +
                        "(id, part_number, name, amount, unit_measure, description, category)" +
                        " VALUES (?,?,?,?,?,?,?)"),
                eq(1L), eq("10002"), eq("NSK Bearing"), eq(120.0), eq("inch"),
                eq("NSK diameter 2"), eq("Bearing")
        );

    }

    @Test
    public void testThatFindOneGeneratesTheCorrectSql() {
        underTest.findOne(1L);
        verify(jdbcTemplate).query(
                eq("SELECT id, part_number, name, amount, unit_measure, description, category FROM products WHERE id = ? LIMIT 1"),
                ArgumentMatchers.<ProductDaoImpl.ProductRowMapper>any(),
                eq(1L)
        );
    }
}
