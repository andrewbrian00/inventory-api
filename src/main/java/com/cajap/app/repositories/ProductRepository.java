package com.cajap.app.repositories;

import com.cajap.app.domain.entities.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long>,
        PagingAndSortingRepository<ProductEntity, Long> {

    Iterable<ProductEntity> amountLessThan(int amount);

    @Query("SELECT p from ProductEntity p where p.amount > ?1")
    Iterable<ProductEntity> findProductsWithAmountGreaterThan(int amount);
}
