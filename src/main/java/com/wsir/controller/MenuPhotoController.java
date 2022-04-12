package com.wsir.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.wsir.entity.MenuPhoto;
import com.wsir.service.MenuPhotoService;
import com.wsir.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/photo")
public class MenuPhotoController {

    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Autowired
    private MenuPhotoService menuPhotoService;

    @PostMapping("/upload")
    public Result upload(@RequestParam MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        long size = file.getSize();

        String uuid = IdUtil.fastSimpleUUID();
        String time = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss").format(LocalDateTime.now());
        String uploadFileName = time + StrUtil.UNDERLINE + uuid + StrUtil.DOT + type;
        File uploadFile = new File(fileUploadPath + uploadFileName); //文件路径

        //先存到磁盘
        if (!uploadFile.getParentFile().exists()) {
            uploadFile.getParentFile().mkdirs(); //存储下载文件目录不存在，则创建
        }

        file.transferTo(uploadFile); //将文件存储到对应位置
        String md5 = SecureUtil.md5(uploadFile); //得到该文件的md5，必须在上传文件后才能得到
        MenuPhoto byMd5 = menuPhotoService.selectByMd5(md5);
        MenuPhoto menuPhoto = new MenuPhoto();
        String url;
        if (byMd5 == null) {
            url = "http://localhost:8088/photo/" + uploadFileName; //新建url
            //存到数据库
            menuPhoto.setType(type);
            menuPhoto.setSize(size / 1024);
            menuPhoto.setMd5(md5);
            menuPhoto.setUrl(url);
            menuPhotoService.insert(menuPhoto);
            byMd5 = menuPhotoService.selectByMd5(md5);
        } else {
            uploadFile.delete(); //若存在则销毁
        }
        return Result.success(byMd5);
    }

    @GetMapping("/{uploadFileName}")
    public void download(@PathVariable String uploadFileName, HttpServletResponse response) throws IOException {
        //获取文件唯一标识
        File downloadFile = new File(fileUploadPath + uploadFileName);
        //设置浏览器输出流
        ServletOutputStream out = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(uploadFileName, StandardCharsets.UTF_8));
        response.setContentType("application/octet-stream");

        //读文件
        out.write(FileUtil.readBytes(downloadFile));
        out.flush();
        out.close();
    }
}
