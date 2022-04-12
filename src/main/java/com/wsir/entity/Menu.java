package com.wsir.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    private Integer menuId;
    private Integer photoId;
    private String dishName;
    private String dishKind;
    private Integer dishAmount;
    private Double price;
    private Integer canId;
    private Integer canWindow;
    private String enable;
    private LocalDateTime addTime;

    private Boolean isNew;
    private Canteen canteen;
    private String url;
    private int chosenAcount = 1; //默认下单数量为1
}
