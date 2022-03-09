package com.example.db.bean;

import com.example.db.entity.Production;
import com.example.db.service.EmployeeService;
import com.example.db.service.ProductionService;
import com.example.db.service.ReadyProductService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.time.LocalDateTime;
import java.util.List;

@Component
@ManagedBean
@SessionScoped
@Getter
@Setter
public class ProductionBean implements BeanService{
    private Long readyProductId;
    private Integer amount;
    private LocalDateTime date;
    private Long employeeId;
    private Production production;
    private List<Production> productions;

    @Autowired
    private ProductionService productionService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ReadyProductService readyProductService;


    @Override
    public void init() {
        productions = productionService.getAll();
    }

    @Override
    public void create() {
        Production production = new Production();
        production.setDate(date);
        production.setReadyProduct(readyProductService.getById(readyProductId));
        production.setEmployee(employeeService.getById(employeeId));
        production.setAmount(amount);
        productionService.save(production);
        clean();
    }

    @Override
    public void delete(Long id) {
        productionService.deleteById(id);
    }

    @Override
    public String navigateToUpdate(Long id) {
        production = productionService.getById(id);
        return "production_update.xhtml?faces-redirect=true";
    }

    @Override
    public void update() {
        productionService.update(production);
        production.setAmount(null);
        production.setEmployee(null);
        production.setReadyProduct(null);
        production.setDate(null);
    }

    @Override
    public void clean() {
        readyProductId = null;
        employeeId = null;
        amount = null;
        date = null;
    }
}
