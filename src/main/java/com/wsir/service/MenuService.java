package com.wsir.service;

import com.wsir.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuService {

    int pageSize(String dishName,
                 String dishKind,
                 int canId,
                 double minPrice,
                 double maxPrice,
                 int minDishAmount,
                 int maxDishAmount);

    List<Menu> selectPage(int pageNum,
                          int pageSize,
                          String dishName,
                          String dishKind,
                          int canId,
                          double minPrice,
                          double maxPrice,
                          int minDishAmount,
                          int maxDishAmount);

    int pageSizeInOrder(int canId, int canWindow);

    List<Menu> selectInOrder(int pageNum,
                             int pageSize,
                             int canId,
                             int canWindow);

    int insert(Menu menu);

    int update(Menu menu);

    int delete(int menuId);

    int updatePhoto(int menuId, int photoId);

    int updateAmount(Menu menu);

    int updateEnable(Menu menu);
}
