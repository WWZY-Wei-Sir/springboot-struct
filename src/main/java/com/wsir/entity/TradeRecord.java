package com.wsir.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradeRecord {
    private Integer recordId;
    private Integer orderId;
    private Integer menuId;
    private Integer amount;

    private String dishName;
    private String dishKind;
}
