package com.example.db.repository;

import com.example.db.entity.SellProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellProductRepository extends JpaRepository<SellProduct, Long> {
}
