package com.wsir.service.impl;

import com.wsir.entity.TradeRecord;
import com.wsir.mapper.TradeRecordMapper;
import com.wsir.service.TradeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeRecordServiceImpl implements TradeRecordService {

    @Autowired
    private TradeRecordMapper tradeRecordMapper;

    @Override
    public TradeRecord selectById(Integer id) {
        return tradeRecordMapper.selectById(id);
    }

    @Override
    public int insert(TradeRecord tradeRecord) {
        return tradeRecordMapper.insert(tradeRecord);
    }
}
