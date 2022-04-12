package com.wsir.mapper;

import com.wsir.entity.Profit;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Mapper
@Repository
public interface ProfitMapper {

    @Select("select * from profit where profit_id = #{profitId}")
    Profit selectById(@Param("profitId") Integer profitId);

    int pageSize(@Param("canId") int canId, @Param("localDate") LocalDate localDate);

    List<Profit> selectPage(@Param("pageNum") int pageNum,
                            @Param("pageSize") int pageSize,
                            @Param("canId") int canId,
                            @Param("localDate") LocalDate localDate);

    @Select("select * from profit where date = #{date} and can_id = #{canId}")
    Profit selectByDate(Profit profit);

    @Insert("insert into profit(date, can_id) values(#{date}, #{canId})")
    int insert(Profit profit);

    @Update("update profit set profit_amount = #{profitAmount} where profit_id = #{profitId}")
    int updateProfit(Profit profit);

}
