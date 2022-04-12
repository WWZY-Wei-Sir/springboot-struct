package com.wsir.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Administrator {
    private Integer administratorId;
    private String administratorPwd;
    private String administratorName;

    private String role;
    private String token;

    @JsonIgnore
    public String getAdministratorPwd() {
        return administratorPwd;
    }

    @JsonProperty
    public void setAdministratorPwd(String administratorPwd) {
        this.administratorPwd = administratorPwd;
    }
}
