package com.paparazzi.fastdfs.controller;

import com.paparazzi.fastdfs.util.FastDFSUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ProjectName: springsession
 * @Package: com.paparazzi.fastdfs
 * @Description: java类作用描述
 * @Author: 张仪
 * @CreateDate: 2021/2/20 21:41
 * @Version: 1.0.0
 * <p>
 * Copyright: Copyright (c) 2021
 */
@Controller
@RequestMapping("/fastDfs")
public class UploadController {

    @RequestMapping("/uploadFile")
    @ResponseBody
    public String uploadFile(MultipartFile uploadFile) throws IOException {

        System.out.println("文件上传开始");

        //获取文件的真实名称
        String originalFilename = uploadFile.getOriginalFilename();

        int i=originalFilename.lastIndexOf(".")+1;
        String extName=originalFilename.substring(i);
        System.out.println(extName);

        byte[] bytes=uploadFile.getBytes();
        String url = FastDFSUtil.fileUpload(bytes, extName);
        System.out.println("url = " + url);

        return "upload success";

    }
}
