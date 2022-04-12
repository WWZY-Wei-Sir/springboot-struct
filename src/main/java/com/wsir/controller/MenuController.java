package com.wsir.controller;

import cn.hutool.core.util.StrUtil;
import com.wsir.entity.Menu;
import com.wsir.service.MenuService;
import com.wsir.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam int pageNum,
                             @RequestParam int pageSize,
                             @RequestParam String dishName,
                             @RequestParam String dishKind,
                             @RequestParam int canId,
                             @RequestParam double minPrice,
                             @RequestParam double maxPrice,
                             @RequestParam int minDishAmount,
                             @RequestParam int maxDishAmount) {
        pageNum = (pageNum - 1) * pageSize;
        List<Menu> profits = menuService.selectPage(pageNum, pageSize, dishName, dishKind,
                canId, minPrice, maxPrice, minDishAmount, maxDishAmount);
        int total = menuService.pageSize(dishName, dishKind, canId, minPrice,
                maxPrice, minDishAmount, maxDishAmount);
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("data", profits);
        return Result.success(map);
    }

    @GetMapping("/selectInOrder")
    public Result selectInOrder(@RequestParam int pageNum,
                                @RequestParam int pageSize,
                                @RequestParam int canId,
                                @RequestParam int canWindow) {
        pageNum = (pageNum - 1) * pageSize;
        List<Menu> menus = menuService.selectInOrder(pageNum, pageSize, canId, canWindow);
        int total = menuService.pageSizeInOrder(canId, canWindow);
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("data", menus);
        return Result.success(map);
    }

    @PostMapping("/insertOrUpdate")
    public Result insertOrUpdate(@RequestBody Menu menu) {
        if (menu.getIsNew()) { //若新增调用Insert
            menuService.insert(menu);
        } else { //否则调用Update
            menuService.update(menu);
        }
        return Result.success();
    }

    @DeleteMapping("/deleteOne/{menuId}")
    public Result deleteOne(@PathVariable Integer menuId) {
        menuService.delete(menuId);
        return Result.success();
    }

    @PostMapping("/deleteMany")
    public Result deleteMany(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            menuService.delete(id);
        }
        return Result.success();
    }

    @GetMapping("/updatePhoto")
    public Result updatePhoto(@RequestParam int menuId, @RequestParam int photoId) {
        menuService.updatePhoto(menuId, photoId);
        return Result.success();
    }

    @PostMapping("/updateAmount")
    public Result updateAmount(@RequestBody List<Menu> menus) {
        for (Menu menu : menus) {
            menuService.updateAmount(menu);
        }
        return Result.success();
    }

    @PostMapping("/updateEnable")
    public Result updateEnable(@RequestBody Menu menu) {
        menuService.updateEnable(menu);
        return Result.success();
    }
}
