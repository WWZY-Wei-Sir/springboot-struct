package com.wsir.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Worker {
    private Integer workId;
    private String workPwd;
    private String workName;
    private String workSex;
    private String workPhone;
    private Integer canId;
    private Integer canWindow;
    private Double salary;
    private LocalDateTime entryTime;

    private String role;
    private String token;
    private Boolean isNew;
    private String canName;

    @JsonIgnore
    public String getWorkPwd() {
        return workPwd;
    }

    @JsonProperty
    public void setWorkPwd(String workPwd) {
        this.workPwd = workPwd;
    }
}
