package com.wsir.service.impl;

import com.wsir.entity.Student;
import com.wsir.mapper.StudentMapper;
import com.wsir.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public int pageSize(String stuId,
                        String stuName,
                        String stuSex,
                        String academy,
                        String majorCourse,
                        int grade,
                        String atClass,
                        String dormitory) {
        return studentMapper.pageSize(stuId, stuName, stuSex, academy,
                majorCourse, grade, atClass, dormitory);
    }

    @Override
    public List<Student> selectPage(int pageNum,
                                    int pageSize,
                                    String stuId,
                                    String stuName,
                                    String stuSex,
                                    String academy,
                                    String majorCourse,
                                    int grade,
                                    String atClass,
                                    String dormitory) {
        return studentMapper.selectPage(pageNum, pageSize, stuId, stuName, stuSex,
                academy, majorCourse, grade, atClass, dormitory);
    }

    @Override
    public int insert(Student student) {
        return studentMapper.insert(student);
    }

    @Override
    public int update(Student student) {
        return studentMapper.update(student);
    }

    @Override
    public int delete(String stuId) {
        return studentMapper.delete(stuId);
    }
}
