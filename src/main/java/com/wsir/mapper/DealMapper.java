package com.wsir.mapper;

import com.wsir.entity.Deal;
import com.wsir.entity.OrderTable;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DealMapper {

    @Select("select * from deal where deal_id = #{id}")
    Deal selectById(@Param("id") Integer id);

    @Select("select * from deal where order_id = #{id}")
    Deal selectByOrderId(@Param("id") Integer id);

    @Insert("insert into deal(order_id, bill_price) values(#{orderId}, #{billPrice})")
    int insert(Deal deal);

    @Update("update deal set is_paid = '是', pay_time = #{payTime} where order_id = #{orderId}")
    int updatePaying(Deal deal);

    @Update("update deal set is_token = '是' where order_id = #{orderId}")
    int updateTaking(Deal deal);

    @Update("update deal set is_token = '已取餐', deal_time = #{dealTime} where order_id = #{orderId}")
    int updateFinish(Deal deal);
}
