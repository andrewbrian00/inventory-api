package com.cajap.app.dao;

import com.cajap.app.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    void create(Product product);

    Optional<Product> findOne(long l);

    List<Product> find();
}
