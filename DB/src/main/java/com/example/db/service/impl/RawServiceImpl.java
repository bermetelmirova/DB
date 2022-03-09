package com.example.db.service.impl;

import com.example.db.entity.Raw;
import com.example.db.repository.RawRepository;
import com.example.db.service.RawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RawServiceImpl implements RawService {
    @Autowired
    private RawRepository rawRepository;

    @Override
    public Raw save(Raw entity) {
        return rawRepository.save(entity);
    }

    @Override
    public Raw deleteById(Long id) {
        Raw raw = getById(id);
        if(raw==null)
            throw new NullPointerException();
        rawRepository.deleteById(id);
        return raw;
    }

    @Override
    public Raw update(Raw entity) {
        Raw raw = getById(entity.getId());
        raw.setRawName(entity.getRawName());
        raw.setAmount(entity.getAmount());
        raw.setPrice(entity.getPrice());
        raw.setUnit(entity.getUnit());
        return rawRepository.save(entity);
    }

    @Override
    public List<Raw> getAll() {
        return rawRepository.findAll();
    }

    @Override
    public Raw getById(Long id) {
        return rawRepository.findById(id).orElse(null);
    }
}
