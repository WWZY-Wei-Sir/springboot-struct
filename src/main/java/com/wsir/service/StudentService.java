package com.wsir.service;

import com.wsir.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentService {

    int pageSize(String stuId,
                 String stuName,
                 String stuSex,
                 String academy,
                 String majorCourse,
                 int grade,
                 String atClass,
                 String dormitory);

    List<Student> selectPage(int pageNum,
                             int pageSize,
                             String stuId,
                             String stuName,
                             String stuSex,
                             String academy,
                             String majorCourse,
                             int grade,
                             String atClass,
                             String dormitory);

    int insert(Student student);

    int update(Student student);

    int delete(String stuId);
}
