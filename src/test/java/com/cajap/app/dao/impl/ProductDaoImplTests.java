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

@ExtendWith(MockitoExtension.class)
public class ProductDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private ProductDaoImpl underTest;

    @Test
    public void testThatCreateProductGeneratesCorrectSql() {
        Product product = TestDataUtil.createTestProduct();

        underTest.create(product);

        verify(jdbcTemplate).update(
                eq("INSERT INTO cajap.products (part_number, name, amount, unit_measure, description, category) VALUES (?,?,?,?,?,?)"),
                eq("10001"),
                eq("NSK Bearing"),
                eq(100.0d),
                eq("inch"),
                eq("NSK diameter 1"),
                eq("Bearing")
        );

    }

    @Test
    public void testThatFindOneGeneratesTheCorrectSql() {
        underTest.findOne(1L);
        verify(jdbcTemplate).query(
                eq("SELECT id, part_number, name, amount, unit_measure, description, category FROM cajap.products WHERE id = ? LIMIT 1"),
                ArgumentMatchers.<ProductDaoImpl.ProductRowMapper>any(),
                eq(1L)
        );
    }

//    @Test
//    public void testThatDeleteAllGeneratesTheCorrectSql() {
//        underTest.truncate();
//        verify(jdbcTemplate).query(
//                eq("SELECT * FROM PRODUCTS"),
//                eq()
//                );
//
//    }
}
