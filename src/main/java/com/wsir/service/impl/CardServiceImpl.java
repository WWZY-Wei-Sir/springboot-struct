package com.wsir.service.impl;

import com.wsir.entity.Administrator;
import com.wsir.entity.Card;
import com.wsir.exception.ServiceException;
import com.wsir.mapper.AdministratorMapper;
import com.wsir.mapper.CardMapper;
import com.wsir.service.CardService;
import com.wsir.util.Constants;
import com.wsir.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardMapper cardMapper;

    @Override
    public Card selectById(int id) {
        return cardMapper.selectById(id);
    }

    @Override
    public Card matchCard(Card card) {
        Card newCard;
        try { //Sql判断是否出错，其它Sql语句简化了这个操作，实现已经在后台测试过
            newCard = cardMapper.matchCard(card);
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_500, "服务器错误");
        }
        if (newCard != null) {
            //生成具有身份role，用户id，解析密钥用户pwd的token
            String token = TokenUtils.createToken(Constants.ROLE_SCHOOL_CARD,
                    Integer.toString(newCard.getCardId()),
                    newCard.getCardPwd());
            newCard.setRole(Constants.ROLE_SCHOOL_CARD);
            newCard.setToken(token);
        } else {
            throw new ServiceException(Constants.CODE_400, "校园卡id或密码错误");
        }
        return newCard;
    }

    @Override
    public int updatePwd(Card card) {
        return cardMapper.updatePwd(card);
    }

    @Override
    public int pageSize(String isLost) {
        return cardMapper.pageSize(isLost);
    }

    @Override
    public List<Card> selectPage(int pageNum, int pageSize, String isLost) {
        return cardMapper.selectPage(pageNum, pageSize, isLost);
    }

    @Override
    public int insert(Card card) {
        return cardMapper.insert(card);
    }

    @Override
    public int update(Card card) {
        return cardMapper.update(card);
    }

    @Override
    public int updateBalance(Card card) {
        //通过cardId找到对应的校园卡
        Card newCard = cardMapper.selectById(card.getCardId());
        //通过card传来的所需要减少的金额，更新数据
        if (newCard.getBalance() < card.getBalance()) {
            throw new ServiceException(Constants.CODE_400, "校园卡余额不足");
        }
        newCard.setBalance(newCard.getBalance() - card.getBalance());
        return cardMapper.updateBalance(newCard);
    }

    @Override
    public int updateIsLost(Card card) {
        return cardMapper.updateIsLost(card);
    }

    @Override
    public int increaseBalance(Card card) {
        //通过cardId找到对应的校园卡
        Card newCard = cardMapper.selectById(card.getCardId());
        //通过card传来的所需要增加的金额，更新数据
        newCard.setBalance(newCard.getBalance() + card.getBalance());
        return cardMapper.updateBalance(newCard);
    }

    @Override
    public int delete(int cardId) {
        return cardMapper.delete(cardId);
    }

    /**
     * 重置密码
     * @param card 根据cardId找到对应
     * @return int
     */
    @Override
    public int rePwd(Card card) {
        return cardMapper.rePwd(card);
    }
}
