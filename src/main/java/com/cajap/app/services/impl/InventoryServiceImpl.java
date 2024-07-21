package com.cajap.app.services.impl;

import com.cajap.app.domain.entities.InventoryEntity;
import com.cajap.app.repositories.InventoryRepository;
import com.cajap.app.services.InventoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class InventoryServiceImpl implements InventoryService {

    private InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public InventoryEntity createInventory(InventoryEntity inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public List<InventoryEntity> findAll() {
        return StreamSupport.stream(inventoryRepository
                                .findAll()
                                .spliterator(),
                        false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<InventoryEntity> findById(Long id) {

        return inventoryRepository.findById(id);
    }
}
