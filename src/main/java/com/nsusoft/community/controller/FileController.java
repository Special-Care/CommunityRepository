package com.nsusoft.community.controller;

import com.nsusoft.community.dto.FileDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

@Controller
public class FileController {
    @ResponseBody
    @RequestMapping("/file/upload")
    public FileDto upload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("editormd-image-file");

        String imgPath = "";

        if (!file.isEmpty() && file.getSize() != 0) {
            String originalFilename = file.getOriginalFilename();
            // 设置上传文件的保存地址目录
            String path = request.getServletContext().getRealPath("/upload/");

            File filePath = new File(path);
            // 如果保存文件的地址不存在，就先创建目录
            if (!filePath.exists())
                filePath.mkdirs();

            // 使用UUID重新命名上传的文件名称
            String newFilename = UUID.randomUUID().toString().replace("-","")
                    +"."+originalFilename.split("\\.")[1];

            try {
                // 使用MultipartFile接口的方法完成文件上传到指定位置
                file.transferTo(new File(path + newFilename));
            } catch (Exception e) {
                e.printStackTrace();
            }
            imgPath = "upload/" + newFilename;
        }

        FileDto fileDto = new FileDto();
        fileDto.setSuccess(1);
        fileDto.setUrl(imgPath);

        return fileDto;
    }
}
