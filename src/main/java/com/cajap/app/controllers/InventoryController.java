package com.cajap.app.controllers;

import com.cajap.app.domain.dto.InventoryDto;
import com.cajap.app.domain.dto.ProductDto;
import com.cajap.app.domain.entities.InventoryEntity;
import com.cajap.app.domain.entities.ProductEntity;
import com.cajap.app.mappers.Mapper;
import com.cajap.app.services.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class InventoryController {

    private InventoryService inventoryService;

    private Mapper<InventoryEntity, InventoryDto> inventoryMapper;

    public InventoryController(InventoryService inventoryService, Mapper<InventoryEntity, InventoryDto> inventoryMapper) {
        this.inventoryService = inventoryService;
        this.inventoryMapper = inventoryMapper;
    }

    @PostMapping(path = "/inventory")
    public ResponseEntity<InventoryDto> createInventory(@RequestBody InventoryDto inventory) {
        InventoryEntity inventoryEntity = inventoryMapper.mapFrom(inventory);
        InventoryEntity savedInventoryEntity = inventoryService.createInventory(inventoryEntity);
        return new ResponseEntity<>(inventoryMapper.mapTo(savedInventoryEntity), HttpStatus.CREATED);

    }

    @GetMapping(path = "/inventory")
    public List<InventoryDto> listInventory() {
        List<InventoryEntity> inventories = inventoryService.findAll();
        return inventories.stream()
                .map(inventoryMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/inventory/{id}")
    public ResponseEntity<InventoryDto> getInventory(@PathVariable("id") Long id) {

        Optional<InventoryEntity> foundItem = inventoryService.findById(id);
        return foundItem.map(
                InventoryEntity -> {
                    InventoryDto inventoryDto = inventoryMapper.mapTo(InventoryEntity);
                    return new ResponseEntity<>(inventoryDto, HttpStatus.OK);
                }
        ).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
}
