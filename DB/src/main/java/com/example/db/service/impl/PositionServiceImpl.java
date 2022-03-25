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
        Position position = getById(id);
        if(position==null)
            throw new NullPointerException();
        positionRepository.deleteById(id);
        return position;
    }

    @Override
    public Position update(Position entity) {
        Position position = getById(entity.getId());
        position.setPosition(entity.getPosition());
        return positionRepository.save(position);
    }

    @Override
    public List<Position> getAll() {
        return positionRepository.findAll();
    }

    @Override
    public Position getById(Long id) {
        return positionRepository.findById(id).orElse(null
        );
    }
}
