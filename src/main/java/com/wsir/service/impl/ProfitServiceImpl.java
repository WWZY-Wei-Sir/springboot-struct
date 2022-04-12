package com.wsir.service.impl;

import com.wsir.entity.Card;
import com.wsir.entity.Profit;
import com.wsir.mapper.ProfitMapper;
import com.wsir.service.ProfitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProfitServiceImpl implements ProfitService {

    @Autowired
    private ProfitMapper profitMapper;

    @Override
    public int pageSize(int canId, LocalDate localDate) {
        return profitMapper.pageSize(canId, localDate);
    }

    @Override
    public List<Profit> selectPage(int pageNum, int pageSize, int canId, LocalDate localDate) {
        return profitMapper.selectPage(pageNum, pageSize, canId, localDate);
    }

    @Override
    public Profit selectByDate(Profit profit) {
        return profitMapper.selectByDate(profit);
    }

    @Override
    public int insert(Profit profit) {
        return profitMapper.insert(profit);
    }

    @Override
    public int updateProfit(Profit profit) {
        profit.setDate(LocalDate.now());
        //通过profitId date找到对应的数据
        Profit newProfit = profitMapper.selectByDate(profit);
        if (newProfit == null) {
            profitMapper.insert(profit);
            newProfit = profitMapper.selectByDate(profit);
        }
        //通过card传来的所需要减少的金额，更新数据
        newProfit.setProfitAmount(newProfit.getProfitAmount() + profit.getProfitAmount());
        return profitMapper.updateProfit(newProfit);
    }
}
