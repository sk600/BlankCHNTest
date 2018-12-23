package com.blankchn.test.feignclient.service.fallback;

import com.blankchn.test.feignclient.model.User;
import com.blankchn.test.feignclient.service.HystrixFeignService;
import org.springframework.stereotype.Component;

/**
 * @author BlankCHN
 * @date 2018-12-20 20:26
 */
@Component
public class HystrixFallback implements HystrixFeignService {
    @Override
    public String addUser(User user) {
        return user.getName()+" 发生熔断";
    }

    @Override
    public String updateUser(User user) {
        return user.getName()+" 发生熔断";
    }
}
