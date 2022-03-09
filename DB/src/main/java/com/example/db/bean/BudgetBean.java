package com.example.db.bean;

import com.example.db.entity.Budget;
import com.example.db.service.BudgetService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@Component
@ManagedBean
@SessionScoped
@Getter
@Setter
public class BudgetBean {
    private Float budgetSum;
    private Double bonus;
    private Double percent;
    private Budget budget;
    private List<Budget> budgets;

    @Autowired
    private BudgetService budgetService;

    private void init() {
        budgets = budgetService.getAll();
    }

    private void create() {
        Budget budget = new Budget();
        budget.setBonus(bonus);
        budget.setBudgetSum(budgetSum);
        budget.setPercent(percent);
        budgetService.save(budget);
        clean();
    }

    private void delete(Long id) {
        budgetService.deleteById(id);
    }

    private String navigateToUpdate(Long id) {
        budget = budgetService.getById(id);
        return "budget_update.xhtml?faces-redirect=true";
    }

    private void update() {
        budgetService.update(budget);
        budget.setBudgetSum(null);
        budget.setBonus(null);
        budget.setPercent(null);
    }

    private void clean() {
        budgetSum = null;
        bonus = null;
        percent = null;
    }
}
