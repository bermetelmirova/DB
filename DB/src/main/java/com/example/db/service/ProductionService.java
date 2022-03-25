package com.example.db.service;

import com.example.db.entity.Production;

public interface ProductionService extends BaseService<Production>{
    Production produce(Production production);
}
