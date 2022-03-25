package com.example.db.service.impl;

import com.example.db.entity.Production;
import com.example.db.entity.Raw;
import com.example.db.entity.ReadyProduct;
import com.example.db.exception.RawException;
import com.example.db.repository.IngredientRepository;
import com.example.db.repository.ProductionRepository;
import com.example.db.service.ProductionService;
import com.example.db.service.ReadyProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionServiceImpl implements ProductionService {
    @Autowired
    private ProductionRepository productionRepository;
    @Autowired
    private RawServiceImpl rawService;
    @Autowired
    private ReadyProductService readyProductService;
    @Autowired
    private IngredientRepository repository;

    @Override
    public Production save(Production entity) {
        return produce(entity);
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

    @Override
    public Production produce(Production production) {
        ReadyProduct readyProduct = readyProductService.getById(production.getReadyProduct().getId());
        List<Raw> raws = repository.raws(readyProduct.getId());
        Float price = 0F;
        for (Raw raw : raws) {
            if (raw.getAmount() < production.getAmount() * repository.getAmount(raw.getId(), readyProduct.getId())) {
                throw new RawException("Сырья недастаточно!");
            }
        }

        for (Raw raw : raws) {
            raw.setAmount(raw
                    .getAmount() - production.getAmount() * repository
                    .getAmount(raw.getId(), readyProduct.getId()));
            rawService.save(raw);
            price += raw.getPrice() * repository.getAmount(raw.getId(), readyProduct.getId());
        }
        price = price/production.getAmount();
        readyProduct.setAmount(production.getAmount() + readyProduct.getAmount());
        readyProduct.setPrice(price);
        readyProductService.save(readyProduct);
        return productionRepository.save(production);
    }
}