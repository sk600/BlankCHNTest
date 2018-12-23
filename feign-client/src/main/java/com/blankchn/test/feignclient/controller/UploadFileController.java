package com.blankchn.test.feignclient.controller;

import com.blankchn.test.feignclient.service.FileUploadFeignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author BlankCHN
 * @date 2018-12-15 14:28
 */
@RestController
@Api("文件上传")
@RequestMapping("/file")
public class UploadFileController {

    @Autowired
    private FileUploadFeignService fileUploadFeignService;

    @PutMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "文件上传", notes = "选择文件上传")
    public String imageUpload(@ApiParam(value = "文件", required = true)MultipartFile file){
        return fileUploadFeignService.fileUpload(file);
    }

}
