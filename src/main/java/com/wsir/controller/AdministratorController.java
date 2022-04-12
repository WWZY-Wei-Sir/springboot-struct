package com.wsir.controller;

import cn.hutool.core.util.StrUtil;
import com.wsir.entity.Administrator;
import com.wsir.service.AdministratorService;
import com.wsir.util.Constants;
import com.wsir.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/administrator")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    @PostMapping("/login")
    public Result matchAdmin(@RequestBody Administrator administrator) {
        if (administrator.getAdministratorId() == 0 || StrUtil.isBlank(administrator.getAdministratorPwd())) {
            return Result.error(Constants.CODE_400, "管理员id为空或密码为空");
        } else {
            Administrator admin = administratorService.matchAdmin(administrator);
            return Result.success(admin);
        }
    }

    @PostMapping("/changePwd")
    public Result changePwd(@RequestBody Administrator administrator) {
        administratorService.updatePwd(administrator);
        return Result.success();
    }

    @PostMapping("/updatePerson")
    public Result updatePerson(@RequestBody Administrator administrator) {
        administratorService.update(administrator);
        return Result.success();
    }
}
