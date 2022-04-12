package com.wsir.mapper;

import com.wsir.entity.Menu;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
@Repository
public interface MenuMapper {

    @Select("select * from menu where menu_id = #{id}")
    Menu selectById(@Param("id") int id);

    int pageSize(@Param("dishName") String dishName,
                 @Param("dishKind") String dishKind,
                 @Param("canId") int canId,
                 @Param("minPrice") double minPrice,
                 @Param("maxPrice") double maxPrice,
                 @Param("minDishAmount") int minDishAmount,
                 @Param("maxDishAmount") int maxDishAmount);

    List<Menu> selectPage(@Param("pageNum") int pageNum,
                          @Param("pageSize") int pageSize,
                          @Param("dishName") String dishName,
                          @Param("dishKind") String dishKind,
                          @Param("canId") int canId,
                          @Param("minPrice") double minPrice,
                          @Param("maxPrice") double maxPrice,
                          @Param("minDishAmount") int minDishAmount,
                          @Param("maxDishAmount") int maxDishAmount);

    @Select("select count(*) from menu where can_id = #{canId} and can_window = #{canWindow} and enable = '是'")
    int pageSizeInOrder(@Param("canId") int canId,
                        @Param("canWindow") int canWindow);

    @Select("select * from menu left join menu_photo on menu.photo_id = menu_photo.photo_id " +
            "where can_id = #{canId} and can_window = #{canWindow} and enable = '是' " +
            "order by price asc limit #{pageNum}, #{pageSize}")
    @Results({
            @Result(property = "canId", column = "can_id"),
            @Result(property = "canteen", column = "can_id", one = @One(select = "com.wsir.mapper.CanteenMapper.selectById"))
    })
    List<Menu> selectInOrder(@Param("pageNum") int pageNum,
                             @Param("pageSize") int pageSize,
                             @Param("canId") int canId,
                             @Param("canWindow") int canWindow);

    @Insert("insert into menu(dish_name, dish_kind, dish_amount, price, can_id, can_window) " +
            "values(#{dishName}, #{dishKind}, #{dishAmount}, #{price}, #{canId}, #{canWindow})")
    int insert(Menu menu);

    @Update("update menu set dish_name = #{dishName}, dish_kind = #{dishKind}, dish_amount = #{dishAmount}, " +
            "price = #{price}, can_id = #{canId}, can_window = #{canWindow} where menu_id = #{menuId}")
    int update(Menu menu);

    @Delete("delete from menu where menu_id = #{menuId}")
    int delete(int menuId);

    @Update("update menu set photo_id = #{photoId} where menu_id = #{menuId}")
    int updatePhoto(@Param("menuId") int menuId, @Param("photoId") int photoId);

    @Update("update menu set dish_amount = #{dishAmount} where menu_id = #{menuId}")
    int updateAmount(Menu menu);

    @Update("update menu set enable = #{enable} where menu_id = #{menuId}")
    int updateEnable(Menu menu);

}
