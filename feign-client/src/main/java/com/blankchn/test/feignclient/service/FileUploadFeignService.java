package com.blankchn.test.feignclient.service;

import com.blankchn.test.feignclient.config.FeignMultipartSupprotConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author BlankCHN
 * @date 2018-12-15 14:21
 */
@FeignClient(value = "data-provider", configuration = FeignMultipartSupprotConfig.class)
public interface FileUploadFeignService {

    @RequestMapping(method = RequestMethod.POST, value = "/uploadFile/server",
    produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
    consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String fileUpload(@RequestPart(value = "file")MultipartFile file);


}
