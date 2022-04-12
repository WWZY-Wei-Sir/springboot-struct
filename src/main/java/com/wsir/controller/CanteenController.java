package com.wsir.controller;

import com.wsir.entity.Canteen;
import com.wsir.entity.Card;
import com.wsir.service.CanteenService;
import com.wsir.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/canteen")
public class CanteenController {

    @Autowired
    private CanteenService canteenService;

    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam int pageNum,
                             @RequestParam int pageSize,
                             @RequestParam int windows) {
        pageNum = (pageNum - 1) * pageSize;
        List<Canteen> canteens = canteenService.selectPage(pageNum, pageSize, windows);
        int total = canteenService.pageSize(windows);
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("data", canteens);
        return Result.success(map);
    }

    @PostMapping("/update")
    public Result insertOrUpdate(@RequestBody Canteen canteen) {
        canteenService.update(canteen);
        return Result.success();
    }

    @GetMapping("/selectById")
    public Result insertOrUpdate(@RequestParam int canId) {
        Canteen canteen = canteenService.selectById(canId);
        return Result.success(canteen);
    }
}
