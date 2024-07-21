package com.cajap.app.repositories;

import com.cajap.app.TestDataUtil;
import com.cajap.app.domain.entities.InventoryEntity;
import com.cajap.app.domain.entities.ProductEntity;
import com.cajap.app.domain.entities.SupplierEntity;
import com.cajap.app.services.ProductService;
import com.cajap.app.services.SupplierService;
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
public class InventoryRepositoryIntegrationTests {

    private InventoryRepository underTest;

    private SupplierService supplierService;
    private ProductService productService;


    @Autowired
    public InventoryRepositoryIntegrationTests(
            InventoryRepository underTest,
            SupplierService supplierService,
            ProductService productService
    ) {
        this.underTest = underTest;
        this.supplierService = supplierService;
        this.productService = productService;
    }

    @Test
    public void testThatInventoryCanBeCreatedAndRecalled() {
        SupplierEntity supplierEntity = TestDataUtil.createTestSupplier();
        ProductEntity productEntity = TestDataUtil.createTestProduct();
        InventoryEntity inventoryEntity = TestDataUtil.createTestInventory(productEntity, supplierEntity);

        underTest.save(inventoryEntity);

        Optional<InventoryEntity> results = underTest.findById(inventoryEntity.getId());
        assertThat(results).isPresent();
        assertThat(results.get()).isEqualTo(inventoryEntity);

    }

    @Test
    public void testThatInventoryCanBeUpdated() {
        SupplierEntity supplierEntity = TestDataUtil.createTestSupplier();
        ProductEntity productEntity = TestDataUtil.createTestProduct();
        InventoryEntity inventoryEntity = TestDataUtil.createTestInventory(productEntity, supplierEntity);

        underTest.save(inventoryEntity);

        inventoryEntity.setItemCount(200);

        if (underTest.existsById(inventoryEntity.getId())) {
            underTest.save(inventoryEntity);
        }


        Optional<InventoryEntity> results = underTest.findById(inventoryEntity.getId());
        assertThat(results).isPresent();
        assertThat(results.get().getItemCount()).isEqualTo(inventoryEntity.getItemCount());
    }

    @Test
    public void testThatInventoryCanBeDeleted() {
        SupplierEntity supplierEntity = TestDataUtil.createTestSupplier();
        ProductEntity productEntity = TestDataUtil.createTestProduct();
        InventoryEntity inventoryEntity = TestDataUtil.createTestInventory(productEntity, supplierEntity);

        underTest.save(inventoryEntity);
        underTest.deleteById(inventoryEntity.getId());

        Optional<InventoryEntity> results = underTest.findById(inventoryEntity.getId());
        assertThat(results).isEmpty();
    }


}
