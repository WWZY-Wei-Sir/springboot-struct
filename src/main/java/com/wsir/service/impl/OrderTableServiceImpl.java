package com.wsir.service.impl;

import com.wsir.entity.OrderTable;
import com.wsir.mapper.OrderTableMapper;
import com.wsir.service.OrderTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderTableServiceImpl implements OrderTableService {

    @Autowired
    private OrderTableMapper orderTableMapper;

    @Override
    public OrderTable selectById(Integer id) {
        return orderTableMapper.selectById(id);
    }

    @Override
    public int pageSize(int id,
                        String role,
                        int orderId,
                        int canId,
                        String isPaid) {
        return orderTableMapper.pageSize(id, role, orderId, canId, isPaid);
    }

    @Override
    public List<OrderTable> selectPage(int pageNum,
                                       int pageSize,
                                       int id,
                                       String role,
                                       int orderId,
                                       int canId,
                                       String isPaid) {
        return orderTableMapper.selectPage(pageNum, pageSize, id, role, orderId, canId, isPaid);
    }

    @Override
    public int insert(OrderTable orderTable) {
        return orderTableMapper.insert(orderTable);
    }

    @Override
    public int selectLastInsert() {
        return orderTableMapper.selectLastInsert();
    }

    @Override
    public int pageSizeForWorker(int canId, int canWindow) {
        return orderTableMapper.pageSizeForWorker(canId, canWindow);
    }

    @Override
    public List<OrderTable> selectPageForWorker(int pageNum,
                                                int pageSize,
                                                int canId,
                                                int canWindow) {
        return orderTableMapper.selectPageForWorker(pageNum, pageSize, canId, canWindow);
    }

    @Override
    public int pageSizeForCardOrComsumer(int cardId,
                                         int cusId,
                                         int canId,
                                         LocalDateTime minDealTime,
                                         LocalDateTime maxDealTime) {
        return orderTableMapper.pageSizeForCardOrComsumer(cardId, cusId, canId, minDealTime, maxDealTime);
    }

    @Override
    public List<OrderTable> selectPageForCardOrComsumer(int pageNum,
                                                        int pageSize,
                                                        int cardId,
                                                        int cusId,
                                                        int canId,
                                                        LocalDateTime minDealTime,
                                                        LocalDateTime maxDealTime) {
        return orderTableMapper.selectPageForCardOrComsumer(pageNum, pageSize, cardId, cusId,
                canId, minDealTime, maxDealTime);
    }
}
