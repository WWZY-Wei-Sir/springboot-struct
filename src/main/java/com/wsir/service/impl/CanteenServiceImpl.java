package com.wsir.service.impl;

import com.wsir.entity.Canteen;
import com.wsir.mapper.CanteenMapper;
import com.wsir.service.CanteenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CanteenServiceImpl implements CanteenService {

    @Autowired
    private CanteenMapper canteenMapper;

    @Override
    public int pageSize(int windows) {
        return canteenMapper.pageSize(windows);
    }

    @Override
    public List<Canteen> selectPage(int pageNum, int pageSize, int windows) {
        return canteenMapper.selectPage(pageNum, pageSize, windows);
    }

    @Override
    public int update(Canteen canteen) {
        return canteenMapper.update(canteen);
    }

    @Override
    public Canteen selectById(Integer canId) {
        return canteenMapper.selectById(canId);
    }
}
