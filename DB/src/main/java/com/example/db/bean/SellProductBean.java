package com.example.db.bean;

import com.example.db.entity.SellProduct;
import com.example.db.service.EmployeeService;
import com.example.db.service.ReadyProductService;
import com.example.db.service.SellProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class SellProductBean implements BeanService {
    private Long readyProductId;
    private Integer amount;
    private Float sum;
    private LocalDateTime date;
    private SellProduct sellProduct;
    private List<SellProduct> sellProducts;
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
    }

    @Override
    public void create() {
        SellProduct sellProduct = new SellProduct();
        sellProduct.setAmount(amount);
        sellProduct.setEmployee(employeeService.getById(employeeId));
        sellProduct.setReadyProduct(readyProductService.getById(readyProductId));
        sellProduct.setSum(sum);
        sellProduct.setDate(date);
        sellProductService.save(sellProduct);
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
}
