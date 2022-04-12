package com.wsir.service;

import com.wsir.entity.MenuPhoto;

public interface MenuPhotoService {

    int insert(MenuPhoto menuPhoto);

    MenuPhoto selectByMd5(String md5);
}
