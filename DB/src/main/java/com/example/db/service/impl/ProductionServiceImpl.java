package com.example.db.service.impl;

import com.example.db.entity.Production;
import com.example.db.repository.ProductionRepository;
import com.example.db.service.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionServiceImpl implements ProductionService {
    @Autowired
    private ProductionRepository productionRepository;

    @Override
    public Production save(Production entity) {
        return productionRepository.save(entity);
    }

    @Override
    public Production deleteById(Long id) {
        Production production = getById(id);
        if (production == null)
            throw new NullPointerException();
        productionRepository.deleteById(id);
        return production;
    }

    @Override
    public Production update(Production entity) {
        Production production = getById(entity.getId());
        production.setEmployee(entity.getEmployee());
        production.setReadyProduct(entity.getReadyProduct());
        production.setAmount(entity.getAmount());
        production.setDate(entity.getDate());
        return productionRepository.save(entity);
    }

    @Override
    public List<Production> getAll() {
        return productionRepository.findAll();
    }

    @Override
    public Production getById(Long id) {
        return productionRepository.findById(id).orElse(null);
    }
}