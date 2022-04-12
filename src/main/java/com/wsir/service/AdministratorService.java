package com.wsir.service;

import com.wsir.entity.Administrator;

public interface AdministratorService {

    Administrator selectById(int id);

    Administrator matchAdmin(Administrator administrator);

    int updatePwd(Administrator administrator);

    int update(Administrator administrator);
}
