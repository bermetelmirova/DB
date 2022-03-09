package com.example.db.service.impl;

import com.example.db.entity.BuyRaw;
import com.example.db.repository.BuyRawRepository;
import com.example.db.service.BuyRawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyRawServiceImpl implements BuyRawService {
    @Autowired
    private BuyRawRepository buyRawRepository;

    @Override
    public BuyRaw save(BuyRaw entity) {
        return buyRawRepository.save(entity);
    }

    @Override
    public BuyRaw deleteById(Long id) {
        BuyRaw buyRaw = getById(id);
        if(buyRaw==null)
            throw new NullPointerException();
        buyRawRepository.deleteById(id);
        return buyRaw;
    }

    @Override
    public BuyRaw update(BuyRaw entity) {
        BuyRaw buyRaw = getById(entity.getId());
        buyRaw.setRaw(entity.getRaw());
        buyRaw.setAmount(entity.getAmount());
        buyRaw.setDate(entity.getDate());
        buyRaw.setEmployee(entity.getEmployee());
        buyRaw.setSum(entity.getSum());
        return buyRawRepository.save(buyRaw);
    }

    @Override
    public List<BuyRaw> getAll() {
        return buyRawRepository.findAll();
    }

    @Override
    public BuyRaw getById(Long id) {
        return buyRawRepository.findById(id).orElse(null);
    }
}
