package com.wsir.service.impl;

import com.wsir.entity.Deal;
import com.wsir.mapper.DealMapper;
import com.wsir.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DealServiceImpl implements DealService {

    @Autowired
    private DealMapper dealMapper;

    @Override
    public Deal selectById(Integer id) {
        return dealMapper.selectById(id);
    }

    @Override
    public int insert(Deal deal) {
        return dealMapper.insert(deal);
    }

    @Override
    public int updatePaying(Deal deal) {
        return dealMapper.updatePaying(deal);
    }

    @Override
    public int updateTaking(Deal deal) {
        return dealMapper.updateTaking(deal);
    }

    @Override
    public int updateFinish(Deal deal) {
        return dealMapper.updateFinish(deal);
    }
}
