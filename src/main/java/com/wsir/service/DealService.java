package com.wsir.service;

import com.wsir.entity.Deal;

public interface DealService {

    Deal selectById(Integer id);

    int insert(Deal deal);

    int updatePaying(Deal deal);

    int updateTaking(Deal deal);

    int updateFinish(Deal deal);
}
