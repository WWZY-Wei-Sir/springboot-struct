package com.wsir.service.impl;

import com.wsir.entity.Customer;
import com.wsir.mapper.CustomerMapper;
import com.wsir.service.CustomerService;
import com.wsir.util.Constants;
import com.wsir.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Customer selectById(Integer id) {
        return customerMapper.selectById(id);
    }

    @Override
    public Customer matchCus(Customer customer) {
        Customer cus = customerMapper.matchCus(customer);
        if (cus == null) {
            customerMapper.insert(customer);
            cus = customerMapper.matchCus(customer);
        }
        String token = TokenUtils.createToken(Constants.ROLE_CUSTOMER,
                Integer.toString(cus.getCusId()),
                Constants.ROLE_CUSTOMER);
        cus.setRole(Constants.ROLE_CUSTOMER);
        cus.setToken(token);
        return cus;
    }

    @Override
    public int insert(Customer customer) {
        return customerMapper.insert(customer);
    }

    @Override
    public int pageSize(LocalDateTime minBind, LocalDateTime maxBind) {
        return customerMapper.pageSize(minBind, maxBind);
    }

    @Override
    public List<Customer> selectPage(int pageNum,
                                     int pageSize,
                                     LocalDateTime minBind,
                                     LocalDateTime maxBind) {
        return customerMapper.selectPage(pageNum, pageSize, minBind, maxBind);
    }

    @Override
    public int updatePhone(Customer customer) {
        return customerMapper.updatePhone(customer);
    }
}
