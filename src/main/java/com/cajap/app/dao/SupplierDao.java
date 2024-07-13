package com.cajap.app.dao;

import com.cajap.app.domain.Supplier;

import java.util.Optional;

public interface SupplierDao {
    void create(Supplier supplier);

    Optional<Supplier> findOne(long supplierId);
}
