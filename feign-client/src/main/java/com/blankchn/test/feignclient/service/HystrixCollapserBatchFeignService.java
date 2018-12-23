package com.blankchn.test.feignclient.service;

import com.blankchn.test.feignclient.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author BlankCHN
 * @date 2018-12-21 15:51
 */
@FeignClient(name = "data-provider", fallback = HystrixCollapserService.class)
public interface HystrixCollapserBatchFeignService {

    @PostMapping("/user/update/batch")
    List<User> updateUserBatch(@RequestBody List<User> users);

}
