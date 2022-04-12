package com.wsir.mapper;

import com.wsir.entity.MenuPhoto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MenuPhotoMapper {

    @Insert("insert into menu_photo(type, size, url, md5) " +
            "values(#{type}, #{size}, #{url}, #{md5})")
    int insert(MenuPhoto menuPhoto);

    @Select("select * from menu_photo where md5 = #{md5}")
    MenuPhoto selectByMd5(@Param("md5") String md5);

}
