package com.example.db.repository;

import com.example.db.entity.ReadyProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadyProductRepository extends JpaRepository<ReadyProduct, Long> {
}
