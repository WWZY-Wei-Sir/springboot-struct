package com.wsir.mapper;

import com.wsir.entity.Canteen;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CanteenMapper {

    @Select("select * from canteen where can_id = #{canId}")
    Canteen selectById(@Param("canId") Integer canId);

    int pageSize(@Param("windows") int windows);

    List<Canteen> selectPage(@Param("pageNum") int pageNum,
                             @Param("pageSize") int pageSize,
                             @Param("windows") int windows);

    @Update("update canteen set window_amount = #{windowAmount} where can_id = #{canId}")
    int update(Canteen canteen);
}
