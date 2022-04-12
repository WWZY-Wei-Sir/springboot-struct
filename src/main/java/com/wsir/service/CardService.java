package com.wsir.service;

import com.wsir.entity.Card;

import java.util.List;

public interface CardService {

    Card selectById(int id);

    Card matchCard(Card card);

    int updatePwd(Card card);

    int pageSize(String isLost);

    List<Card> selectPage(int pageNum, int pageSize, String isLost);

    int insert(Card card);

    int update(Card card);

    int updateBalance(Card card);

    int updateIsLost(Card card);

    int increaseBalance(Card card);

    int delete(int cardId);

    int rePwd(Card card);
}
