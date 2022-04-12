package com.wsir.controller;

import cn.hutool.core.util.StrUtil;
import com.wsir.entity.Card;
import com.wsir.service.CardService;
import com.wsir.util.Constants;
import com.wsir.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping("/login")
    public Result matchCard(@RequestBody Card card) {
        if (StrUtil.isBlank(card.getStuId()) || StrUtil.isBlank(card.getCardPwd())) { //输入为空（后台测试时有效）
            return Result.error(Constants.CODE_400, "学生id为空或密码为空");
        } else {
            Card newCard = cardService.matchCard(card);
            return Result.success(newCard);
        }
    }

    @PostMapping("/changePwd")
    public Result changePwd(@RequestBody Card card) {
        cardService.updatePwd(card);
        return Result.success();
    }

    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam int pageNum,
                             @RequestParam int pageSize,
                             @RequestParam String isLost) {
        if (StrUtil.isBlank(isLost)) {
            isLost = null;
        }
        pageNum = (pageNum - 1) * pageSize;
        List<Card> cards = cardService.selectPage(pageNum, pageSize, isLost);
        int total = cardService.pageSize(isLost);
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("data", cards);
        return Result.success(map);
    }

    @PostMapping("/insertOrUpdate")
    public Result insertOrUpdate(@RequestBody Card card) {
        if (card.getIsNew()) {
            cardService.insert(card);
        } else {
            cardService.update(card);
        }
        return Result.success();
    }

    @DeleteMapping("/deleteOne/{cardId}")
    public Result deleteOne(@PathVariable Integer cardId) {
        cardService.delete(cardId);
        return Result.success();
    }

    @PostMapping("/deleteMany")
    public Result deleteMany(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            cardService.delete(id);
        }
        return Result.success();
    }

    @PostMapping("/rePwd")
    public Result rePwd(@RequestBody Card card) {
        cardService.rePwd(card);
        return Result.success();
    }

    @PostMapping("/updateBalance")
    public Result updateBalance(@RequestBody Card card) {
        cardService.updateBalance(card);
        return Result.success();
    }

    @PostMapping("/increaseBalance")
    public Result increaseBalance(@RequestBody Card card) {
        cardService.increaseBalance(card);
        return Result.success();
    }

    @PostMapping("/updateIsLost")
    public Result updateIsLost(@RequestBody Card card) {
        cardService.updateIsLost(card);
        return Result.success();
    }
}
