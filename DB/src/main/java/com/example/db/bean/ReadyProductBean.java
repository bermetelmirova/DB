package com.example.db.bean;

import com.example.db.entity.ReadyProduct;
import com.example.db.service.ReadyProductService;
import com.example.db.service.UnitService;
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
public class ReadyProductBean implements BeanService {
    private String product;
    private Long unitId;
    private Float price;
    private Integer amount;
    private ReadyProduct readyProduct;
    private List<ReadyProduct> readyProducts;

    @Autowired
    private ReadyProductService readyProductService;
    @Autowired
    private UnitService unitService;

    @Override
    public void init() {
        readyProducts = readyProductService.getAll();
    }

    @Override
    public void create() {
        ReadyProduct readyProduct = new ReadyProduct();
        readyProduct.setUnit(unitService.getById(unitId));
        readyProduct.setProduct(product);
        readyProduct.setAmount(amount);
        readyProduct.setPrice(price);
        readyProductService.save(readyProduct);
    }

    @Override
    public void delete(Long id) {
        readyProductService.deleteById(id);
    }

    @Override
    public String navigateToUpdate(Long id) {
        readyProduct = readyProductService.getById(id);
        return "ready_product_update.xhtml?faces-redirect=true";
    }

    @Override
    public void update() {
        readyProductService.update(readyProduct);
        readyProduct.setPrice(null);
        readyProduct.setAmount(null);
        readyProduct.setProduct(null);
        readyProduct.setUnit(null);
    }

    @Override
    public void clean() {
        product = null;
        unitId = null;
        price = null;
        amount = null;
    }
}
