package com.wsir.controller;

import com.wsir.entity.Deal;
import com.wsir.service.DealService;
import com.wsir.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deal")
public class DealController {

    @Autowired
    private DealService dealService;

    @PostMapping("/insert")
    public Result insert(@RequestBody Deal deal) {
        dealService.insert(deal);
        return Result.success();
    }

    @PostMapping("/updatePaying")
    public Result updatePaying(@RequestBody Deal deal) {
        dealService.updatePaying(deal);
        return Result.success();
    }

    @PostMapping("/updateTaking")
    public Result updateTaking(@RequestBody Deal deal) {
        dealService.updateTaking(deal);
        return Result.success();
    }

    @PostMapping("/updateFinish")
    public Result updateFinish(@RequestBody Deal deal) {
        dealService.updateFinish(deal);
        return Result.success();
    }
}
