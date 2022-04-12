package com.wsir.service.impl;

import com.wsir.entity.Menu;
import com.wsir.entity.Profit;
import com.wsir.mapper.MenuMapper;
import com.wsir.mapper.ProfitMapper;
import com.wsir.service.MenuService;
import com.wsir.service.ProfitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public int pageSize(String dishName,
                        String dishKind,
                        int canId,
                        double minPrice,
                        double maxPrice,
                        int minDishAmount,
                        int maxDishAmount) {
        return menuMapper.pageSize(dishName, dishKind, canId, minPrice,
                maxPrice, minDishAmount, maxDishAmount);
    }

    @Override
    public List<Menu> selectPage(int pageNum,
                                 int pageSize,
                                 String dishName,
                                 String dishKind,
                                 int canId,
                                 double minPrice,
                                 double maxPrice,
                                 int minDishAmount,
                                 int maxDishAmount) {
        return menuMapper.selectPage(pageNum, pageSize, dishName, dishKind, canId,
                minPrice, maxPrice, minDishAmount, maxDishAmount);
    }

    @Override
    public int pageSizeInOrder(int canId, int canWindow) {
        return menuMapper.pageSizeInOrder(canId, canWindow);
    }

    @Override
    public List<Menu> selectInOrder(int pageNum,
                                    int pageSize,
                                    int canId,
                                    int canWindow) {
        return menuMapper.selectInOrder(pageNum, pageSize, canId, canWindow);
    }

    @Override
    public int insert(Menu menu) {
        return menuMapper.insert(menu);
    }

    @Override
    public int update(Menu menu) {
        return menuMapper.update(menu);
    }

    @Override
    public int delete(int menuId) {
        return menuMapper.delete(menuId);
    }

    @Override
    public int updatePhoto(int menuId, int photoId) {
        return menuMapper.updatePhoto(menuId, photoId);
    }

    @Override
    public int updateAmount(Menu menu) {
        return menuMapper.updateAmount(menu);
    }

    @Override
    public int updateEnable(Menu menu) {
        return menuMapper.updateEnable(menu);
    }
}
