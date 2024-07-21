package com.cajap.app.services.impl;

import com.cajap.app.domain.entities.SupplierEntity;
import com.cajap.app.repositories.SupplierRepository;
import com.cajap.app.services.SupplierService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SupplierServiceImpl implements SupplierService {

    private SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public SupplierEntity createUpdate(SupplierEntity supplierEntity) {
        return supplierRepository.save(supplierEntity);
    }

    @Override
    public List<SupplierEntity> findAll() {
        return StreamSupport.stream(supplierRepository
                                .findAll()
                                .spliterator(),
                        false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SupplierEntity> findById(Long id) {
        return supplierRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return supplierRepository.existsById(id);
    }

    @Override
    public SupplierEntity partialUpdate(Long id, SupplierEntity supplierEntity) {
        supplierEntity.setId(id);
        return supplierRepository.findById(id).map(
                existingSupplier -> {
                    Optional.ofNullable(supplierEntity.getName())
                            .ifPresent(existingSupplier::setName);
                    return supplierRepository.save(existingSupplier);
                }
        ).orElseThrow(() -> new RuntimeException("Supplier does not exists"));
    }
}
