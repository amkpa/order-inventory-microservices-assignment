package com.korber.inventory_service.repository;


import com.korber.inventory_service.model.ProductBatch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductBatchRepository extends JpaRepository<ProductBatch, Long> {

    List<ProductBatch> findByProductIdOrderByExpiryDateAsc(String productId);
}
