package com.wsir.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String stuId;
    private String stuName;
    private String stuSex;
    private String stuPhone;
    private String academy;
    private String majorCourse;
    private Integer grade;
    private String atClass;
    private String dormitory;

    private Boolean isNew;
}
