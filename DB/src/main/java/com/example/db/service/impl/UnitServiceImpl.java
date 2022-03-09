package com.example.db.service.impl;

import com.example.db.entity.Unit;
import com.example.db.repository.UnitRepository;
import com.example.db.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitServiceImpl implements UnitService {
    @Autowired
    private UnitRepository unitRepository;

    @Override
    public Unit save(Unit entity) {
        return unitRepository.save(entity);
    }

    @Override
    public Unit deleteById(Long id) {
        Unit unit = getById(id);
        if (unit == null)
            throw new NullPointerException();
        unitRepository.deleteById(id);
        return unit;
    }

    @Override
    public Unit update(Unit entity) {
        Unit unit = getById(entity.getId());
        unit.setValueName(entity.getValueName());
        return unitRepository.save(unit);
    }

    @Override
    public List<Unit> getAll() {
        return unitRepository.findAll();
    }

    @Override
    public Unit getById(Long id) {
        return unitRepository.findById(id).orElse(null);
    }
}
