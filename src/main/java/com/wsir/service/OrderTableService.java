package com.wsir.service;

import com.wsir.entity.OrderTable;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderTableService {

    OrderTable selectById(Integer id);

    int pageSize(int id,
                 String role,
                 int orderId,
                 int canId,
                 String isPaid);

    List<OrderTable> selectPage(int pageNum,
                                int pageSize,
                                int id,
                                String role,
                                int orderId,
                                int canId,
                                String isPaid);

    int insert(OrderTable orderTable);

    int selectLastInsert();

    int pageSizeForWorker(int canId, int canWindow);

    List<OrderTable> selectPageForWorker(int pageNum,
                                         int pageSize,
                                         int canId,
                                         int canWindow);

    int pageSizeForCardOrComsumer(int cardId,
                                  int cusId,
                                  int canId,
                                  LocalDateTime minDealTime,
                                  LocalDateTime maxDealTime);

    List<OrderTable> selectPageForCardOrComsumer(int pageNum,
                                                 int pageSize,
                                                 int cardId,
                                                 int cusId,
                                                 int canId,
                                                 LocalDateTime minDealTime,
                                                 LocalDateTime maxDealTime);
}
