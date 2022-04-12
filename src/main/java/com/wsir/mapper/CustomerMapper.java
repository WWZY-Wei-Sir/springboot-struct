package com.wsir.mapper;

import com.wsir.entity.Customer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
@Repository
public interface CustomerMapper {

    @Select("select * from customer where cus_id = #{id}")
    Customer selectById(@Param("id") Integer id);

    @Select("select * from customer where cus_phone = #{cusPhone}")
    Customer matchCus(Customer customer);

    int pageSize(@Param("minBind") LocalDateTime minBind, @Param("maxBind") LocalDateTime maxBind);

    List<Customer> selectPage(@Param("pageNum") int pageNum,
                              @Param("pageSize") int pageSize,
                              @Param("minBind") LocalDateTime minBind,
                              @Param("maxBind") LocalDateTime maxBind);

    @Insert("insert into customer(cus_phone) values(#{cusPhone})")
    int insert(Customer customer);

    @Update("update customer set cus_phone = #{cusPhone} where cus_id = #{cusId}")
    int updatePhone(Customer customer);
}
