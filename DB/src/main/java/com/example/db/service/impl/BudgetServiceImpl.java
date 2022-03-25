package com.example.db.service.impl;

import com.example.db.entity.Budget;
import com.example.db.repository.BudgetRepository;
import com.example.db.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetServiceImpl implements BudgetService {
    @Autowired
    private BudgetRepository budgetRepository;

    @Override
    public Budget save(Budget entity) {
        return budgetRepository.save(entity);
    }

    @Override
    public Budget deleteById(Long id) {
        Budget budget = getById(id);
        if (budget == null)
            throw new NullPointerException();
        budgetRepository.deleteById(id);
        return budget;
    }

    @Override
    public Budget update(Budget entity) {
        Budget budget = getById(entity.getId());
        budget.setBudgetSum(entity.getBudgetSum());
        budget.setBonus(entity.getBonus());
        budget.setPercent(entity.getPercent());
        return budgetRepository.save(budget);
    }

    @Override
    public List<Budget> getAll() {
        return budgetRepository.findAll();
    }

    @Override
    public Budget getById(Long id) {
        return budgetRepository.findById(id).orElse(null);
    }
}
