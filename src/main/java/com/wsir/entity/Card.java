package com.wsir.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    private Integer cardId;
    private String cardPwd;
    private String stuId;
    private Double balance;
    private String isLost;
    private LocalDateTime createTime;

    private String role;
    private String token;
    private Boolean isNew;

    @JsonIgnore
    public String getCardPwd() {
        return cardPwd;
    }

    @JsonProperty
    public void setCardPwd(String cardPwd) {
        this.cardPwd = cardPwd;
    }
}
