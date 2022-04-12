package com.wsir.mapper;

import com.wsir.entity.TradeRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TradeRecordMapper {

    @Select("select * from trade_record where record_id = #{id}")
    TradeRecord selectById(@Param("id") Integer id);

    @Select("select * from trade_record left join menu on trade_record.menu_id = menu.menu_id where order_id = #{orderId}")
    TradeRecord selectByOrderId(@Param("orderId") Integer orderId);

    @Insert("insert into trade_record(order_id, menu_id, amount) " +
            "values(#{orderId}, #{menuId}, #{amount})")
    int insert(TradeRecord tradeRecord);
}
