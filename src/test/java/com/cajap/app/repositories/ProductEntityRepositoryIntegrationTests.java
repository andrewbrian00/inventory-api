package com.cajap.app.repositories;

import com.cajap.app.TestDataUtil;
import com.cajap.app.domain.entities.ProductEntity;
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
public class ProductEntityRepositoryIntegrationTests {

    private ProductRepository underTest;

    @Autowired
    public ProductEntityRepositoryIntegrationTests(ProductRepository underTest) {
        this.underTest = underTest;

    }

    @Test
    public void testThatProductCanBeCreatedAndRecalled() {
        ProductEntity productEntity = TestDataUtil.createTestProduct();
        underTest.save(productEntity);
        Optional<ProductEntity> result = underTest.findById(productEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(productEntity);
    }

    @Test
    public void testThatMultipleProductCanBeCreatedAndRecalled() {
        ProductEntity productEntityA = TestDataUtil.createTestProductA();
        ProductEntity productEntityB = TestDataUtil.createTestProductB();
        ProductEntity productEntityC = TestDataUtil.createTestProductC();

        underTest.save(productEntityA);
        underTest.save(productEntityB);
        underTest.save(productEntityC);

        Iterable<ProductEntity> results = underTest.findAll();
        assertThat(results).hasSize(3)
                .containsExactly(productEntityA, productEntityB, productEntityC);

    }

    @Test
    public void testThatProductCanBeUpdated() {
        ProductEntity productEntityA = TestDataUtil.createTestProductA();
        underTest.save(productEntityA);
        productEntityA.setAmount(100.0d);
        underTest.save(productEntityA);

        Optional<ProductEntity> result = underTest.findById(productEntityA.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(productEntityA);
    }

    @Test
    public void testThatProductCanBeDeleted() {
        ProductEntity productEntity = TestDataUtil.createTestProductA();
        underTest.save(productEntity);
        underTest.deleteById(productEntity.getId());

        Optional<ProductEntity> result = underTest.findById(productEntity.getId());
        assertThat(result).isEmpty();
    }

    @Test
    public void testThatGetProductsWithAmountLessThan() {
        ProductEntity productEntityA = TestDataUtil.createTestProductA();
        ProductEntity productEntityB = TestDataUtil.createTestProductB();
        ProductEntity productEntityC = TestDataUtil.createTestProductC();

        underTest.save(productEntityA);
        underTest.save(productEntityB);
        underTest.save(productEntityC);

        Iterable<ProductEntity> result = underTest.amountLessThan(200);
        assertThat(result).containsExactly(productEntityA, productEntityB, productEntityC);

    }

    @Test
    public void testThatGetProductsWithAmountGreaterThan() {
        ProductEntity productEntityA = TestDataUtil.createTestProductA();
        ProductEntity productEntityB = TestDataUtil.createTestProductB();
        ProductEntity productEntityC = TestDataUtil.createTestProductC();

        underTest.save(productEntityA);
        underTest.save(productEntityB);
        underTest.save(productEntityC);

        Iterable<ProductEntity> result = underTest.findProductsWithAmountGreaterThan(139);
        assertThat(result).containsExactly(productEntityC);
    }
}
