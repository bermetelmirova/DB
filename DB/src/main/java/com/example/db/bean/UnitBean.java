package com.example.db.bean;

import com.example.db.entity.Unit;
import com.example.db.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UnitBean implements BeanService {

    private String valueName;
    private Unit unit;
    private List<Unit> units;

    @Autowired
    private UnitService unitService;

    @Override
    public void init() {
        units = unitService.getAll();
    }

    @Override
    public void create() {
        Unit unit = new Unit();
        unit.setValueName(valueName);
    }

    @Override
    public void delete(Long id) {
        unitService.deleteById(id);
    }

    @Override
    public String navigateToUpdate(Long id) {
        unit =unitService.getById(id);
        return "unit_update.xhtml?faces-redirect=true";
    }

    @Override
    public void update() {
        unitService.update(unit);
        unit.setValueName(null);
    }

    @Override
    public void clean() {
        valueName = null;
    }
}
