package com.blankchn.test.feignclient.service;

import com.blankchn.test.feignclient.model.User;
import com.blankchn.test.feignclient.service.fallback.HystrixFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author BlankCHN
 * @date 2018-12-20 20:25
 */
@FeignClient(name = "data-provider", fallback = HystrixFallback.class)
public interface HystrixFeignService {

    @RequestMapping(value = "/user/add", method = RequestMethod.GET)
    String addUser(User user);

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    String updateUser(@RequestBody User user);

}
