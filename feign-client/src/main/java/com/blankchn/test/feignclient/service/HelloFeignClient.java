package com.blankchn.test.feignclient.service;

import com.blankchn.test.feignclient.config.HelloFeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author BlankCHN
 * @date 2018-12-14 19:39
 */
@Service("helloFeignClient")
@FeignClient(name = "github-client", url = "https://api.github.com", configuration = HelloFeignClientConfig.class)
public interface HelloFeignClient {

    @RequestMapping(value = "/search/repositories", method = RequestMethod.GET)
    ResponseEntity<byte[]> searchRepo(@RequestParam("q")String queryStr);

}
