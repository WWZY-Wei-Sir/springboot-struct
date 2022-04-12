package com.wsir.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Deal {
    private Integer dealId;
    private Integer orderId;
    private Double billPrice;
    private String isPaid;
    private String isToken;
    private LocalDateTime payTime;
    private LocalDateTime dealTime;
}
