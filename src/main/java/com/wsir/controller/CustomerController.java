package com.wsir.controller;

import cn.hutool.core.util.StrUtil;
import com.wsir.entity.Administrator;
import com.wsir.entity.Customer;
import com.wsir.entity.Profit;
import com.wsir.service.AdministratorService;
import com.wsir.service.CustomerService;
import com.wsir.util.Constants;
import com.wsir.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/login")
    public Result matchAdmin(@RequestBody Customer customer) {
        if (StrUtil.isBlank(customer.getCusPhone())) {
            return Result.error(Constants.CODE_400, "游客登录电话号码为空");
        } else {
            Customer cus = customerService.matchCus(customer);
            return Result.success(cus);
        }
    }

    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam int pageNum,
                             @RequestParam int pageSize,
                             @RequestParam String minBindStr,
                             @RequestParam String maxBindStr) {
        LocalDateTime minBind = null;
        LocalDateTime maxBind = null;
        if (!StrUtil.isBlank(minBindStr)) {
            LocalDate localDate = LocalDate.parse(minBindStr);
            minBind = localDate.atStartOfDay();
        }
        if (!StrUtil.isBlank(maxBindStr)) {
            LocalDate localDate = LocalDate.parse(maxBindStr);
            maxBind = localDate.atStartOfDay();
        }
        pageNum = (pageNum - 1) * pageSize;
        List<Customer> customers = customerService.selectPage(pageNum, pageSize, minBind, maxBind);
        int total = customerService.pageSize(minBind, maxBind);
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("data", customers);
        return Result.success(map);
    }

    @PostMapping("/updatePhone")
    public Result updatePhone(@RequestBody Customer customer) {
        customerService.updatePhone(customer);
        return Result.success();
    }
}
