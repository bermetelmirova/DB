package com.example.db.service;

import com.example.db.entity.SellProduct;

public interface SellProductService extends BaseService<SellProduct>{
    SellProduct sellProduct(SellProduct sellProduct);
    Float setPrice(Long id);
}
