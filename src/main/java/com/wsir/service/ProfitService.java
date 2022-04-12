package com.wsir.service;

import com.wsir.entity.Profit;

import java.time.LocalDate;
import java.util.List;

public interface ProfitService {

    int pageSize(int canId, LocalDate localDate);

    List<Profit> selectPage(int pageNum, int pageSize, int canId, LocalDate localDate);

    Profit selectByDate(Profit profit);

    int insert(Profit profit);

    int updateProfit(Profit profit);
}
