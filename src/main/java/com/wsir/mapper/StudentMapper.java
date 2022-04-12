package com.wsir.mapper;

import com.wsir.entity.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {

    int pageSize(@Param("stuId") String stuId,
                 @Param("stuName") String stuName,
                 @Param("stuSex") String stuSex,
                 @Param("academy") String academy,
                 @Param("majorCourse") String majorCourse,
                 @Param("grade") int grade,
                 @Param("atClass") String atClass,
                 @Param("dormitory") String dormitory);

    List<Student> selectPage(@Param("pageNum") int pageNum,
                             @Param("pageSize") int pageSize,
                             @Param("stuId") String stuId,
                             @Param("stuName") String stuName,
                             @Param("stuSex") String stuSex,
                             @Param("academy") String academy,
                             @Param("majorCourse") String majorCourse,
                             @Param("grade") int grade,
                             @Param("atClass") String atClass,
                             @Param("dormitory") String dormitory);

    @Insert("insert into student(stu_id, stu_name, stu_sex, stu_phone, academy," +
            "major_course, grade, at_class, dormitory) values(#{stuId}, #{stuName}, #{stuSex}," +
            "#{stuPhone}, #{academy}, #{majorCourse}, #{grade}, #{atClass}, #{dormitory})")
    int insert(Student student);

    @Update("update student set stu_name = #{stuName}, stu_sex = #{stuSex}, stu_phone = #{stuPhone}," +
            "academy = #{academy}, major_course = #{majorCourse}, grade = #{grade}," +
            "at_class = #{atClass}, dormitory = #{dormitory} where stu_id = #{stuId}")
    int update(Student student);

    @Delete("delete from student where stu_id = #{stuId}")
    int delete(String stuId);
}
