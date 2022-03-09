package com.example.db.service.impl;

import com.example.db.entity.Ingredient;
import com.example.db.repository.IngredientRepository;
import com.example.db.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public Ingredient save(Ingredient entity) {
        return ingredientRepository.save(entity);
    }

    @Override
    public Ingredient deleteById(Long id) {
        Ingredient ingredient = getById(id);
        if (ingredient == null)
            throw new NullPointerException();
        ingredientRepository.deleteById(id);
        return ingredient;
    }

    @Override
    public Ingredient update(Ingredient entity) {
        Ingredient ingredient = getById(entity.getId());
        ingredient.setRaw(entity.getRaw());
        ingredient.setAmount(entity.getAmount());
        ingredient.setReadyProduct(entity.getReadyProduct());
        return ingredientRepository.save(ingredient);
    }

    @Override
    public List<Ingredient> getAll() {
        return ingredientRepository.findAll();
    }

    @Override
    public Ingredient getById(Long id) {
        return ingredientRepository.findById(id).orElse(null);
    }
}
