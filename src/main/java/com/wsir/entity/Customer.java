package com.wsir.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private Integer cusId;
    private String cusPhone;
    private LocalDateTime bindTime;

    private String role;
    private String token;
}
