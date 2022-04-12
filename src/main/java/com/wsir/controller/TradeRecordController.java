package com.wsir.controller;

import com.wsir.entity.TradeRecord;
import com.wsir.service.TradeRecordService;
import com.wsir.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tradeRecord")
public class TradeRecordController {

    @Autowired
    private TradeRecordService tradeRecordService;

    @PostMapping("/insert")
    public Result insert(@RequestBody List<TradeRecord> tradeRecords) {
        for (TradeRecord tradeRecord : tradeRecords) {
            tradeRecordService.insert(tradeRecord);
        }
        return Result.success();
    }
}
