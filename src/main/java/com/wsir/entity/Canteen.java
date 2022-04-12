package com.wsir.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Canteen {
    private Integer canId;
    private String canName;
    private Integer windowAmount;
}
