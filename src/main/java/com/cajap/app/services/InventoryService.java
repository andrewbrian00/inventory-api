package com.cajap.app.services;

import com.cajap.app.domain.entities.InventoryEntity;

import java.util.List;
import java.util.Optional;

public interface InventoryService {

    InventoryEntity createInventory(InventoryEntity inventory);

    List<InventoryEntity> findAll();

    Optional<InventoryEntity> findById(Long id);

}
