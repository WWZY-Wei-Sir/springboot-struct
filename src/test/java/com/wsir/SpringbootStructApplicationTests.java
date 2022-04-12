package com.wsir;

import com.wsir.controller.AdministratorController;
import com.wsir.entity.*;
import com.wsir.mapper.*;
import com.wsir.util.Constants;
import com.wsir.util.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class SpringbootStructApplicationTests {

    @Autowired
    ProfitMapper profitMapper;

    @Autowired
    CanteenMapper canteenMapper;

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    OrderTableMapper orderTableMapper;

    @Autowired
    TradeRecordMapper tradeRecordMapper;

    @Test
    void contextLoads() {
        int i = orderTableMapper.pageSizeForCardOrComsumer(2100001, -999, -999, null, null);
        System.out.println(i);
        List<OrderTable> orderTables = orderTableMapper.selectPageForCardOrComsumer(0, 10, 2100001,
                -999, -999, null, null);
        for (OrderTable orderTable : orderTables) {
            System.out.println(orderTable);
        }
        List<Profit> profits = profitMapper.selectPage(0, 10, -999, null);
        for (Profit profit : profits) {
            System.out.println(profit);
        }
    }


}
