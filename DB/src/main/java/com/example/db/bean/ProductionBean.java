package com.example.db.bean;

import com.example.db.entity.Employee;
import com.example.db.entity.Production;
import com.example.db.entity.ReadyProduct;
import com.example.db.exception.RawException;
import com.example.db.service.EmployeeService;
import com.example.db.service.ProductionService;
import com.example.db.service.ReadyProductService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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
    private List<Employee> employees;
    private List<ReadyProduct> readyProducts;

    @Autowired
    private ProductionService productionService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ReadyProductService readyProductService;


    @Override
    public void init() {
        productions = productionService.getAll();
        employees = employeeService.getAll();
        readyProducts = readyProductService.getAll();
    }

    @Override
    public void create() {
        Production production = new Production();
        production.setDate(date);
        production.setReadyProduct(readyProductService.getById(readyProductId));
        production.setEmployee(employeeService.getById(employeeId));
        production.setAmount(amount);
        try {
            productionService.save(production);
        }catch (RawException e){
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Exception",e.getMessage()));
            PrimeFaces.current().ajax().update("messages");
        }

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
        production.setReadyProduct(readyProductService.getById(readyProductId));
        production.setEmployee(employeeService.getById(employeeId));
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
