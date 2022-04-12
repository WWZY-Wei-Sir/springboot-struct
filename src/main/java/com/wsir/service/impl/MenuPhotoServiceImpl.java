package com.wsir.service.impl;

import com.wsir.entity.MenuPhoto;
import com.wsir.mapper.MenuPhotoMapper;
import com.wsir.service.MenuPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuPhotoServiceImpl implements MenuPhotoService {

    @Autowired
    private MenuPhotoMapper menuPhotoMapper;

    @Override
    public int insert(MenuPhoto menuPhoto) {
        return menuPhotoMapper.insert(menuPhoto);
    }

    @Override
    public MenuPhoto selectByMd5(String md5) {
        return menuPhotoMapper.selectByMd5(md5);
    }
}
