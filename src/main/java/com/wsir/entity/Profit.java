package com.wsir.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profit {
    private Integer profitId;
    private LocalDate date;
    private Integer canId;
    private Double profitAmount;

    private String canName;
}
