package com.wsir.mapper;

import com.wsir.entity.Administrator;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdministratorMapper {

    @Select("select * from administrator where administrator_id = #{id}")
    Administrator selectById(@Param("id") int id);

    @Select("select * from administrator where administrator_id = #{administratorId} " +
            "and administrator_pwd = #{administratorPwd}")
    Administrator matchAdmin(Administrator administrator);

    @Update("update administrator set administrator_pwd = #{administratorPwd} " +
            "where administrator_id = #{administratorId}")
    int updatePwd(Administrator administrator);

    @Update("update administrator set administrator_name = #{administratorName} " +
            "where administrator_id = #{administratorId}")
    int update(Administrator administrator);
}
