package com.example.db.service.impl;

import com.example.db.entity.Budget;
import com.example.db.entity.ReadyProduct;
import com.example.db.entity.SellProduct;
import com.example.db.exception.SellProductException;
import com.example.db.repository.SellProductRepository;
import com.example.db.service.BudgetService;
import com.example.db.service.ReadyProductService;
import com.example.db.service.SellProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellProductServiceImpl implements SellProductService {
    @Autowired
    private SellProductRepository sellProductRepository;

    @Autowired
    private BudgetService budgetService;

    @Autowired
    private ReadyProductService readyProductService;

    @Override
    public SellProduct save(SellProduct entity) {
        return sellProduct(entity);
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

    @Override
    public SellProduct sellProduct(SellProduct sellProduct) {
        if (sellProduct.getReadyProduct() == null)
            throw new NullPointerException();
        ReadyProduct readyProduct = readyProductService.getById(sellProduct.getReadyProduct().getId());
        Budget budget = budgetService.getById(1L);
        float totalSum = sellProduct.getSum() * (float) sellProduct.getAmount();
        if (readyProduct.getAmount() < sellProduct.getAmount())
            throw new SellProductException("Недастаточно продуктов для продажи");
        budget.setBudgetSum(budget.getBudgetSum() + totalSum);
        readyProduct.setAmount(readyProduct.getAmount() - sellProduct.getAmount());
        budgetService.save(budget);
        readyProductService.save(readyProduct);
        sellProductRepository.save(sellProduct);
        return sellProduct;
    }

    @Override
    public Float setPrice(Long id) {
        Float productPrice = readyProductService.getById(id).getPrice();
        Float budgetPercent = budgetService.getById(1L).getPercent();
        return productPrice + (productPrice * budgetPercent) / 100;
    }
}
