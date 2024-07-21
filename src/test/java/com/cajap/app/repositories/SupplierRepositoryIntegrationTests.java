package com.cajap.app.repositories;

import com.cajap.app.TestDataUtil;
import com.cajap.app.domain.entities.SupplierEntity;
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
public class SupplierRepositoryIntegrationTests {

    private SupplierRepository underTest;

    @Autowired
    public SupplierRepositoryIntegrationTests(SupplierRepository underTest) {
        this.underTest = underTest;

    }

    @Test
    public void testThatSupplierCanBeCreatedAndRecalled() {
        SupplierEntity supplierEntity = TestDataUtil.createTestSupplier();
        underTest.save(supplierEntity);
        Optional<SupplierEntity> results = underTest.findById(supplierEntity.getId());
        assertThat(results).isPresent();
        assertThat(results.get()).isEqualTo(supplierEntity);

    }
}