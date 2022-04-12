package com.wsir.controller;

import cn.hutool.core.util.StrUtil;
import com.wsir.entity.Profit;
import com.wsir.service.ProfitService;
import com.wsir.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/profit")
public class ProfitController {

    @Autowired
    private ProfitService profitService;

    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam int pageNum,
                             @RequestParam int pageSize,
                             @RequestParam int canId,
                             @RequestParam String localDateStr) {
        LocalDate localDate = null;
        if (!StrUtil.isBlank(localDateStr)) {
            localDate = LocalDate.parse(localDateStr);
        }
        pageNum = (pageNum - 1) * pageSize;
        List<Profit> profits = profitService.selectPage(pageNum, pageSize, canId, localDate);
        int total = profitService.pageSize(canId, localDate);
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("data", profits);
        return Result.success(map);
    }

    @PostMapping("/updateProfit")
    public Result updateProfit(@RequestBody Profit profit) {
        profitService.updateProfit(profit);
        return Result.success();
    }
}
