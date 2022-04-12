package com.wsir.service.impl;

import com.wsir.entity.Administrator;
import com.wsir.exception.ServiceException;
import com.wsir.mapper.AdministratorMapper;
import com.wsir.service.AdministratorService;
import com.wsir.util.Constants;
import com.wsir.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private AdministratorMapper administratorMapper;

    @Override
    public Administrator selectById(int id) {
        return administratorMapper.selectById(id);
    }

    @Override
    public Administrator matchAdmin(Administrator administrator) {
        Administrator admin;
        try {
            admin = administratorMapper.matchAdmin(administrator);
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_500, "服务器错误");
        }
        if (admin != null) {
            String token = TokenUtils.createToken(Constants.ROLE_ADMINISTRATOR,
                    Integer.toString(admin.getAdministratorId()),
                    admin.getAdministratorPwd());
            admin.setRole(Constants.ROLE_ADMINISTRATOR);
            admin.setToken(token);
        } else {
            throw new ServiceException(Constants.CODE_400, "管理员id或密码错误");
        }
        return admin;
    }

    @Override
    public int updatePwd(Administrator administrator) {
        return administratorMapper.updatePwd(administrator);
    }

    @Override
    public int update(Administrator administrator) {
        return administratorMapper.update(administrator);
    }
}
