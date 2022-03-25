package com.example.db.service.impl;

import com.example.db.entity.Budget;
import com.example.db.entity.BuyRaw;
import com.example.db.entity.Raw;
import com.example.db.exception.BudgetException;
import com.example.db.repository.BuyRawRepository;
import com.example.db.service.BudgetService;
import com.example.db.service.BuyRawService;
import com.example.db.service.RawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyRawServiceImpl implements BuyRawService {
    @Autowired
    private BuyRawRepository buyRawRepository;

    @Autowired
    private BudgetService budgetService;
    @Autowired
    private RawService rawService;

    @Override
    public BuyRaw save(BuyRaw entity) {
        return buyraw(entity);
    }

    @Override
    public BuyRaw deleteById(Long id) {
        BuyRaw buyRaw = getById(id);
        if (buyRaw == null)
            throw new NullPointerException();
        buyRawRepository.deleteById(id);
        return buyRaw;
    }

    @Override
    public BuyRaw update(BuyRaw entity) {
        BuyRaw buyRaw = getById(entity.getId());
        buyRaw.setRaw(entity.getRaw());
        buyRaw.setAmount(entity.getAmount());
        buyRaw.setDate(entity.getDate());
        buyRaw.setEmployee(entity.getEmployee());
        buyRaw.setSum(entity.getSum());
        return buyRawRepository.save(buyRaw);
    }

    @Override
    public List<BuyRaw> getAll() {
        return buyRawRepository.findAll();
    }

    @Override
    public BuyRaw getById(Long id) {
        return buyRawRepository.findById(id).orElse(null);
    }

    @Override
    public BuyRaw buyraw(BuyRaw buyRaw) {
        float totalPrice = buyRaw.getSum() * (float) buyRaw.getAmount();
        Budget budget = budgetService.getById(1L);
        Raw raw = rawService.getById(buyRaw.getRaw().getId());
        if (totalPrice > budget.getBudgetSum())
            throw new BudgetException("Недастотачно средств");
        budget.setBudgetSum(budget.getBudgetSum() - totalPrice);
        raw.setAmount(raw.getAmount() + buyRaw.getAmount());
        budgetService.save(budget);
        rawService.save(raw);
        buyRawRepository.save(buyRaw);
        return buyRaw;
    }
}
