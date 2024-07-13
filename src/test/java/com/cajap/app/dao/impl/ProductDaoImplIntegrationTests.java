package com.cajap.app.dao.impl;

import com.cajap.app.TestDataUtil;
import com.cajap.app.domain.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductDaoImplIntegrationTests {

    private final ProductDaoImpl underTest;

    @Autowired
    public ProductDaoImplIntegrationTests(ProductDaoImpl underTest) {
        this.underTest = underTest;

    }

    @Test
    public void testThatProductCanBeCreatedAndRecalled() {
        Product product = TestDataUtil.createTestProductA();
        underTest.create(product);
        Optional<Product> results = underTest.findOne(product.getId());
        assertThat(results).isPresent();
        assertThat(results.get()).isEqualTo(product);
    }

    @Test
    public void testThatMultipleProductCanBeCreatedAndRecalled() {
        Product productA = TestDataUtil.createTestProductA();
        Product productB = TestDataUtil.createTestProductB();
        Product productC = TestDataUtil.createTestProductC();

        underTest.create(productA);
        underTest.create(productB);
        underTest.create(productC);

        List<Product> results = underTest.find();
        assertThat(results).hasSize(3)
                .containsExactly(productA, productB, productC);

    }
}
