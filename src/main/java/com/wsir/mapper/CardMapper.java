package com.wsir.mapper;

import com.wsir.entity.Administrator;
import com.wsir.entity.Card;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CardMapper {

    @Select("select * from card where card_id = #{id}")
    Card selectById(@Param("id") int id);

    @Select("select * from card where stu_id = #{stuId} " +
            "and card_pwd = #{cardPwd}")
    Card matchCard(Card card);

    @Update("update card set card_pwd = #{cardPwd} where stu_id = #{stuId}")
    int updatePwd(Card card);

    int pageSize(@Param("isLost") String isLost);

    List<Card> selectPage(@Param("pageNum") int pageNum,
                          @Param("pageSize") int pageSize,
                          @Param("isLost") String isLost);

    @Insert("insert into card(card_pwd, stu_id, balance, is_lost) values(#{cardPwd}, #{stuId}, #{balance}, #{isLost})")
    int insert(Card card);

    @Update("update card set stu_id = #{stuId}, balance = #{balance}, is_lost = #{isLost}," +
            "create_time = #{createTime} where card_id = #{cardId}")
    int update(Card card);

    @Update("update card set balance = #{balance} where card_id = #{cardId}")
    int updateBalance(Card card);

    @Update("update card set is_lost = #{isLost} where card_id = #{cardId}")
    int updateIsLost(Card card);

    @Delete("delete from card where card_id = #{cardId}")
    int delete(int cardId);

    @Update("update card set card_pwd = #{cardPwd} where card_id = #{cardId}")
    int rePwd(Card card);
}
