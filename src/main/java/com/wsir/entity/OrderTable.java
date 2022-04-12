package com.wsir.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderTable {
    private Integer orderId;
    private Integer cardId;
    private Integer cusId;
    private Integer canId;
    private Integer canWindow;
    private LocalDateTime orderTime;

    private String canName;
    private Deal deal;
    private List<TradeRecord> tradeRecords;
}
