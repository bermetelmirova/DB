package com.example.db.bean;

import com.example.db.entity.Employee;
import com.example.db.entity.ReadyProduct;
import com.example.db.entity.SellProduct;
import com.example.db.exception.SellProductException;
import com.example.db.service.EmployeeService;
import com.example.db.service.ReadyProductService;
import com.example.db.service.SellProductService;
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
public class SellProductBean implements BeanService {
    private Long readyProductId;
    private Integer amount;
    private Float sum;
    private LocalDateTime date;
    private SellProduct sellProduct;
    private List<SellProduct> sellProducts;
    private List<ReadyProduct> readyProducts;
    private List<Employee> employees;
    private Long employeeId;

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ReadyProductService readyProductService;
    @Autowired
    private SellProductService sellProductService;


    @Override
    public void init() {
        sellProducts = sellProductService.getAll();
        readyProducts = readyProductService.getAll();
        employees = employeeService.getAll();
    }

    @Override
    public void create() {
        SellProduct sellProduct = new SellProduct();
        sellProduct.setAmount(amount);
        sellProduct.setEmployee(employeeService.getById(employeeId));
        sellProduct.setReadyProduct(readyProductService.getById(readyProductId));
        sellProduct.setSum(sum);
        sellProduct.setDate(date);
        try {
            sellProductService.save(sellProduct);
        } catch (SellProductException sellProductException) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Exception", sellProductException.getMessage()));
            PrimeFaces.current().ajax().update("messages");
        } finally {
            clean();
        }
    }

    @Override
    public void delete(Long id) {
        sellProductService.deleteById(id);
    }

    @Override
    public String navigateToUpdate(Long id) {
        sellProduct = sellProductService.getById(id);
        return "sell_product_update.xhtml?faces-redirect=true";
    }

    @Override
    public void update() {
        sellProduct.setReadyProduct(readyProductService.getById(readyProductId));
        sellProduct.setEmployee(employeeService.getById(employeeId));
        sellProductService.update(sellProduct);
        sellProduct.setEmployee(null);
        sellProduct.setReadyProduct(null);
        sellProduct.setDate(null);
        sellProduct.setSum(null);
        sellProduct.setAmount(null);
    }

    @Override
    public void clean() {
        readyProductId = null;
        amount = null;
        sum = null;
        date = null;
        employeeId = null;
    }

    public void setPrice() {
        sum = sellProductService.setPrice(readyProductId);
    }
}
