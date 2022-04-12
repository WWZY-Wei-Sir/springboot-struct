package com.wsir.service;

import com.wsir.entity.Customer;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomerService {

    Customer selectById(Integer id);

    Customer matchCus(Customer customer);

    int insert(Customer customer);

    int pageSize(LocalDateTime minBind, LocalDateTime maxBind);

    List<Customer> selectPage(int pageNum,
                              int pageSize,
                              LocalDateTime minBind,
                              LocalDateTime maxBind);

    int updatePhone(Customer customer);
}
