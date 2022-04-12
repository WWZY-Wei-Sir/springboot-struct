package com.wsir.mapper;

import com.wsir.entity.Deal;
import com.wsir.entity.OrderTable;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
@Repository
public interface OrderTableMapper {

    @Select("select * from order_table where order_id = #{id}")
    OrderTable selectById(@Param("id") Integer id);

    int pageSize(@Param("id") int id,
                 @Param("role") String role,
                 @Param("orderId") int orderId,
                 @Param("canId") int canId,
                 @Param("isPaid") String isPaid);

    List<OrderTable> selectPage(@Param("pageNum") int pageNum,
                                @Param("pageSize") int pageSize,
                                @Param("id") int id,
                                @Param("role") String role,
                                @Param("orderId") int orderId,
                                @Param("canId") int canId,
                                @Param("isPaid") String isPaid);

    @Insert("insert into order_table(card_id, cus_id, can_id, can_window) " +
            "values(#{cardId}, #{cusId}, #{canId}, #{canWindow})")
    int insert(OrderTable order);

    @Select("SELECT LAST_INSERT_ID()")
    int selectLastInsert();

    /**
     * 员工查询位于他所工作的食堂与对应窗口的所有待处理订单
     * @param canId
     * @param canWindow
     * @return
     */
    @Select("select count(*) from order_table left join deal on order_table.order_id = deal.order_id " +
            "where can_id = #{canId} and can_window = #{canWindow} and " +
            "is_paid = '是' and is_token = '否'")
    int pageSizeForWorker(@Param("canId") int canId,
                          @Param("canWindow") int canWindow);

    @Select("select * from order_table left join deal on order_table.order_id = deal.order_id " +
            "left join canteen on order_table.can_id = canteen.can_id " +
            "where order_table.can_id = #{canId} and can_window = #{canWindow} and " +
            "is_paid = '是' and is_token = '否' limit #{pageNum}, #{pageSize}")
    @Results({
            @Result(property = "orderId", column = "order_id"),
            @Result(one = @One(select = "com.wsir.mapper.DealMapper.selectByOrderId"),
                    property = "deal", column = "order_id", javaType = Deal.class),
            @Result(many = @Many(select = "com.wsir.mapper.TradeRecordMapper.selectByOrderId"),
                    property = "tradeRecords", column = "order_id", javaType = List.class)
    })
    List<OrderTable> selectPageForWorker(@Param("pageNum") int pageNum,
                                         @Param("pageSize") int pageSize,
                                         @Param("canId") int canId,
                                         @Param("canWindow") int canWindow);

    /**
     * 校园卡和游客消费查询位于历史消费记录
     * @param cardId
     * @param cusId
     * @param canId
     * @param minDealTime
     * @param maxDealTime
     * @return
     */
    int pageSizeForCardOrComsumer(@Param("cardId") int cardId,
                                  @Param("cusId") int cusId,
                                  @Param("canId") int canId,
                                  @Param("minDealTime") LocalDateTime minDealTime,
                                  @Param("maxDealTime") LocalDateTime maxDealTime);

    List<OrderTable> selectPageForCardOrComsumer(@Param("pageNum") int pageNum,
                                                 @Param("pageSize") int pageSize,
                                                 @Param("cardId") int cardId,
                                                 @Param("cusId") int cusId,
                                                 @Param("canId") int canId,
                                                 @Param("minDealTime") LocalDateTime minDealTime,
                                                 @Param("maxDealTime") LocalDateTime maxDealTime);
}
