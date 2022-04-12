package com.wsir.service;

import com.wsir.entity.Canteen;

import java.util.List;

public interface CanteenService {
    int pageSize(int windows);

    List<Canteen> selectPage(int pageNum, int pageSize, int windows);

    int update(Canteen canteen);

    Canteen selectById(Integer canId);
}
