package com.laywerspringboot.edition.controller;

import com.laywerspringboot.edition.Utils.OSSUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author:小七
 * @createTime:2020-10-29-23-15
 */
@RestController()
@CrossOrigin()
@Slf4j
@Api(description = "图片api",value = "上传下载")
public class ImageController {
    /**
     * 上传图片到 oss
     * @param imgFile
     * @return
     */
    @ApiOperation(value = "上传图片到oss")
    @PostMapping(value="/uploadImgToOSS")
    public String uploadImgToOSS(MultipartFile imgFile) {
        return OSSUtils.uploadImg(imgFile);
    }
}
