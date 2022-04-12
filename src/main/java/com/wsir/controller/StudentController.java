package com.wsir.controller;

import cn.hutool.core.util.StrUtil;
import com.wsir.entity.Student;
import com.wsir.service.StudentService;
import com.wsir.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam int pageNum,
                             @RequestParam int pageSize,
                             @RequestParam String stuId,
                             @RequestParam String stuName,
                             @RequestParam String stuSex,
                             @RequestParam String academy,
                             @RequestParam String majorCourse,
                             @RequestParam int grade,
                             @RequestParam String atClass,
                             @RequestParam String dormitory) {
        if (StrUtil.isBlank(stuId)) {
            stuId = null;
        }
        if (StrUtil.isBlank(stuName)) {
            stuName = null;
        }
        if (StrUtil.isBlank(stuSex)) {
            stuSex = null;
        }
        if (StrUtil.isBlank(academy)) {
            academy = null;
        }
        if (StrUtil.isBlank(majorCourse)) {
            majorCourse = null;
        }
        if (StrUtil.isBlank(atClass)) {
            atClass = null;
        }
        if (StrUtil.isBlank(dormitory)) {
            dormitory = null;
        }
        pageNum = (pageNum - 1) * pageSize;
        List<Student> students = studentService.selectPage(pageNum, pageSize, stuId, stuName,
                stuSex, academy, majorCourse, grade, atClass, dormitory);
        int total = studentService.pageSize(stuId, stuName, stuSex, academy,
                majorCourse, grade, atClass, dormitory);
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("data", students);
        return Result.success(map);
    }

    @PostMapping("/insertOrUpdate")
    public Result insertOrUpdate(@RequestBody Student student) {
        if (student.getIsNew()) {
            studentService.insert(student);
        } else {
            studentService.update(student);
        }
        return Result.success();
    }

    @DeleteMapping("/deleteOne/{stuId}")
    public Result deleteOne(@PathVariable String stuId) {
        studentService.delete(stuId);
        return Result.success();
    }

    @PostMapping("/deleteMany")
    public Result deleteMany(@RequestBody List<String> ids) {
        for (String id : ids) {
            studentService.delete(id);
        }
        return Result.success();
    }
}
