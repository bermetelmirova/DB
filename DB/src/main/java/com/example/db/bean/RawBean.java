package com.example.db.bean;

import com.example.db.entity.Raw;
import com.example.db.entity.Unit;
import com.example.db.service.RawService;
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
public class RawBean implements BeanService{
    private String rawName;
    private Long unitId;
    private Float price;
    private Integer amount;
    private Raw raw;
    private List<Raw> raws;
    private List<Unit> units;

    @Autowired
    private RawService rawService;
    @Autowired
    private UnitService unitService;

    @Override
    public void init() {
        raws = rawService.getAll();
        units = unitService.getAll();
    }

    @Override
    public void create() {
        Raw raw = new Raw();
        raw.setUnit(unitService.getById(unitId));
        raw.setRawName(rawName);
        raw.setAmount(amount);
        raw.setPrice(price);
        rawService.save(raw);
        clean();
    }

    @Override
    public void delete(Long id) {
        rawService.deleteById(id);
    }

    @Override
    public String navigateToUpdate(Long id) {
        raw = rawService.getById(id);
        return "raw_update.xhtml?faces-redirect=true";
    }

    @Override
    public void update() {
        raw.setUnit(unitService.getById(unitId));
        rawService.update(raw);
        raw.setPrice(null);
        raw.setAmount(null);
        raw.setRawName(null);
        raw.setUnit(null);

    }

    @Override
    public void clean() {
        rawName = null;
        unitId = null;
        price = null;
        amount = null;
    }
}
