package com.cajap.app.services;

import com.cajap.app.domain.entities.ProductEntity;
import com.cajap.app.domain.entities.SupplierEntity;

import java.util.List;
import java.util.Optional;

public interface SupplierService {

    SupplierEntity createUpdate(SupplierEntity supplierEntity);

    List<SupplierEntity> findAll();

    Optional<SupplierEntity> findById(Long id);

    boolean isExists(Long id);

    SupplierEntity partialUpdate(Long id, SupplierEntity supplierEntity);
}
