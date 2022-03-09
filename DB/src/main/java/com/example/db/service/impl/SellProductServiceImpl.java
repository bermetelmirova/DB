package com.example.db.service.impl;

import com.example.db.entity.SellProduct;
import com.example.db.repository.SellProductRepository;
import com.example.db.service.SellProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SellProductServiceImpl implements SellProductService {
    @Autowired
    private SellProductRepository sellProductRepository;

    @Override
    public SellProduct save(SellProduct entity) {
        return sellProductRepository.save(entity);
    }

    @Override
    public SellProduct deleteById(Long id) {
        SellProduct sellProduct = getById(id);
        if (sellProduct == null)
            throw new NullPointerException();
        sellProductRepository.deleteById(id);
        return sellProduct;
    }

    @Override
    public SellProduct update(SellProduct entity) {
        SellProduct sellProduct = getById(entity.getId());
        sellProduct.setReadyProduct(entity.getReadyProduct());
        sellProduct.setAmount(entity.getAmount());
        sellProduct.setDate(entity.getDate());
        sellProduct.setSum(entity.getSum());
        sellProduct.setEmployee(entity.getEmployee());
        sellProductRepository.save(sellProduct);
        return sellProduct;
    }

    @Override
    public List<SellProduct> getAll() {
        return sellProductRepository.findAll();
    }

    @Override
    public SellProduct getById(Long id) {
        return sellProductRepository.findById(id).orElse(null);
    }
}
