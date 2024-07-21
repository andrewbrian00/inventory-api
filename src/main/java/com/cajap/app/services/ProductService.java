package com.cajap.app.services;

import com.cajap.app.domain.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    ProductEntity createUpdate(ProductEntity productEntity);

    List<ProductEntity> findAll();

    Page<ProductEntity> findAll(Pageable pageable);

    Optional<ProductEntity> findById(Long id);

    boolean isExists(Long id);

    ProductEntity partialUpdate(Long id, ProductEntity productEntity);

    void deleteById(Long id);
}
