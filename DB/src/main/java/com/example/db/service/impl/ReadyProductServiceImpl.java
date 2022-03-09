package com.example.db.service.impl;

import com.example.db.entity.ReadyProduct;
import com.example.db.repository.ReadyProductRepository;
import com.example.db.service.ReadyProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReadyProductServiceImpl implements ReadyProductService {
    @Autowired
    private ReadyProductRepository readyProductRepository;

    @Override
    public ReadyProduct save(ReadyProduct entity) {
        return readyProductRepository.save(entity);
    }

    @Override
    public ReadyProduct deleteById(Long id) {
        ReadyProduct readyProduct = getById(id);
        if (readyProduct == null)
            throw new NullPointerException();
        readyProductRepository.deleteById(id);
        return readyProduct;
    }

    @Override
    public ReadyProduct update(ReadyProduct entity) {
        ReadyProduct readyProduct = getById(entity.getId());
        readyProduct.setProduct(entity.getProduct());
        readyProduct.setAmount(entity.getAmount());
        readyProduct.setPrice(entity.getPrice());
        readyProduct.setUnit(entity.getUnit());
        return readyProductRepository.save(entity);
    }

    @Override
    public List<ReadyProduct> getAll() {
        return readyProductRepository.findAll();
    }

    @Override
    public ReadyProduct getById(Long id) {
        return readyProductRepository.findById(id).orElse(null);
    }
}
