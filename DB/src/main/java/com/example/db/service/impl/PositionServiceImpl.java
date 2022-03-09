package com.example.db.service.impl;

import com.example.db.entity.Position;
import com.example.db.repository.PositionRepository;
import com.example.db.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionRepository positionRepository;

    @Override
    public Position save(Position entity) {
        return positionRepository.save(entity);
    }

    @Override
    public Position deleteById(Long id) {
        return null;
    }

    @Override
    public Position update(Position entity) {
        return null;
    }

    @Override
    public List<Position> getAll() {
        return null;
    }

    @Override
    public Position getById(Long id) {
        return positionRepository.findById(id).orElse(null
        );
    }
}
