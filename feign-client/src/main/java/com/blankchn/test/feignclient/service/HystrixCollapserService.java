package com.blankchn.test.feignclient.service;

import com.blankchn.test.feignclient.model.User;
import com.netflix.hystrix.HystrixCollapser.Scope;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @author BlankCHN
 * @date 2018-12-21 15:32
 */
@Service
public class HystrixCollapserService implements IHystrixCollapserService, HystrixCollapserBatchFeignService {

    @Autowired
    private HystrixCollapserBatchFeignService hystrixCollapserBatchFeignService;

    @Override
    @HystrixCollapser(batchMethod = "collapsingBatch", scope = Scope.REQUEST,
            collapserProperties = {@HystrixProperty(name = "timerDelayInMilliseconds", value = "1000")})
    public Future<User> collapsing(String name) {
        return null;
    }

    @Override
    @HystrixCollapser(batchMethod = "collapsingBatch", scope = Scope.REQUEST,
            collapserProperties = {@HystrixProperty(name = "timerDelayInMilliseconds", value = "1000")})
    public User collapsingSync(String name) {
        return null;
    }

    @Override
    @HystrixCollapser(batchMethod = "collapsingBatchGlobal", scope = Scope.GLOBAL,
            collapserProperties = {@HystrixProperty(name = "timerDelayInMilliseconds", value = "1000")})
    public Future<User> collapsingGlobal(String name) {
        return null;
    }


    @HystrixCommand
    public List<User> collapsingBatch(List<String> names) {
        System.out.println("collapsingBatch 线程" + Thread.currentThread().getName() + " 请求数量为" + names.size());
        List<User> users = new ArrayList<>();
        names.forEach(name -> {
            User user = new User();
            user.setName(name);
            users.add(user);
        });

        return hystrixCollapserBatchFeignService.updateUserBatch(users);
    }

    @HystrixCommand
    public List<User> collapsingBatchGlobal(List<String> names) {
        System.out.println("collapsingBatchGlobal 线程" + Thread.currentThread().getName() + " 请求数量为" + names.size());
        List<User> users = new ArrayList<>();
        names.forEach(name -> {
            User user = new User();
            user.setName(name);
            users.add(user);
        });

        return hystrixCollapserBatchFeignService.updateUserBatch(users);
    }


    @Override
    public List<User> updateUserBatch(List<User> users) {
        System.out.println("update-user-batch 发生熔断");
        users.forEach(user -> {
            user.setName(user.getName() + " 发生熔断");
        });

        return users;
    }
}
