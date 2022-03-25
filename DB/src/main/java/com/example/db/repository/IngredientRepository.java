package com.example.db.repository;

import com.example.db.entity.Ingredient;
import com.example.db.entity.Raw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    @Query(value = "select r from Raw r join Ingredient i on r.id=i.raw.id where i.readyProduct.id = ?1")
    List<Raw> raws(Long productId);

    @Query("select i.amount from Ingredient i where i.raw.id = ?1 and i.readyProduct.id = ?2")
    Integer getAmount(Long rawId, Long productId);
}
