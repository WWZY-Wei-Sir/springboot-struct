package com.wsir.controller;

import cn.hutool.core.util.StrUtil;
import com.wsir.entity.Menu;
import com.wsir.entity.OrderTable;
import com.wsir.service.OrderTableService;
import com.wsir.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderTableController {

    @Autowired
    private OrderTableService orderTableService;

    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam int pageNum,
                             @RequestParam int pageSize,
                             @RequestParam int id,
                             @RequestParam String role,
                             @RequestParam int orderId,
                             @RequestParam int canId,
                             @RequestParam String isPaid) {
        if (StrUtil.isBlank(isPaid)) {
            isPaid = null;
        }
        pageNum = (pageNum - 1) * pageSize;
        List<OrderTable> orderTables = orderTableService.selectPage(pageNum, pageSize, id, role, orderId, canId, isPaid);
        int total = orderTableService.pageSize(id, role, orderId, canId, isPaid);
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("data", orderTables);
        return Result.success(map);
    }

    @PostMapping("/insert")
    public Result insert(@RequestBody OrderTable orderTable) {
        orderTableService.insert(orderTable);
        return Result.success(orderTableService.selectLastInsert());
    }

    @GetMapping("/selectPageForWorker")
    public Result selectPageForWorker(@RequestParam int pageNum,
                                      @RequestParam int pageSize,
                                      @RequestParam int canId,
                                      @RequestParam int canWindow) {
        pageNum = (pageNum - 1) * pageSize;
        List<OrderTable> orderTables = orderTableService.selectPageForWorker(pageNum, pageSize, canId, canWindow);
        int total = orderTableService.pageSizeForWorker(canId, canWindow);
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("data", orderTables);
        return Result.success(map);
    }

    @GetMapping("/selectPageForCardOrComsumer")
    public Result selectPageForCardOrComsumer(@RequestParam int pageNum,
                                              @RequestParam int pageSize,
                                              @RequestParam int cardId,
                                              @RequestParam int cusId,
                                              @RequestParam int canId,
                                              @RequestParam String minDealTimeStr,
                                              @RequestParam String maxDealTimeStr) {
        LocalDateTime minDealTime = null;
        LocalDateTime maxDealTime = null;
        if (!StrUtil.isBlank(minDealTimeStr) ) {
            LocalDate localDate = LocalDate.parse(minDealTimeStr);
            minDealTime = localDate.atStartOfDay();
        }
        if (!StrUtil.isBlank(maxDealTimeStr) ) {
            LocalDate localDate = LocalDate.parse(maxDealTimeStr);
            maxDealTime = localDate.atStartOfDay();
        }
        pageNum = (pageNum - 1) * pageSize;
        List<OrderTable> orderTables = orderTableService.selectPageForCardOrComsumer(pageNum, pageSize,
                cardId, cusId, canId, minDealTime, maxDealTime);
        int total = orderTableService.pageSizeForCardOrComsumer(cardId, cusId, canId, minDealTime, maxDealTime);
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("data", orderTables);
        return Result.success(map);
    }
}
