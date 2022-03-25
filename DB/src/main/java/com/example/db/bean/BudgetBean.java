package com.example.db.bean;

import com.example.db.entity.Budget;
import com.example.db.service.BudgetService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@Component
@ManagedBean
@ViewScoped
public class BudgetBean {
    @Getter
    @Setter
    private Float budgetSum;
    @Getter
    @Setter
    private Float bonus;
    @Getter
    @Setter
    private Float percent;
    @Getter
    @Setter
    private Budget budget;
    @Getter
    @Setter
    private List<Budget> budgets;

    @Autowired
    private BudgetService budgetService;

    public void init() {
        budgets = budgetService.getAll();
    }

    public void create() {
        Budget budget = new Budget();
        budget.setBonus(bonus);
        budget.setBudgetSum(budgetSum);
        budget.setPercent(percent);
        budgetService.save(budget);
        clean();
    }

    public void delete(Long id) {
        budgetService.deleteById(id);
    }

    public String navigateToUpdate(Long id) {
        budget = budgetService.getById(id);
        return "budget_update.xhtml?faces-redirect=true";
    }

    public void update() {
        budgetService.update(budget);
        budget = new Budget();
    }

    private void clean() {
        budgetSum = null;
        bonus = null;
        percent = null;
    }
}
