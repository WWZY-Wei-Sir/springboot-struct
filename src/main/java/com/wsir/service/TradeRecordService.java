package com.wsir.service;

import com.wsir.entity.TradeRecord;

public interface TradeRecordService {

    TradeRecord selectById(Integer id);

    int insert(TradeRecord tradeRecord);
}
