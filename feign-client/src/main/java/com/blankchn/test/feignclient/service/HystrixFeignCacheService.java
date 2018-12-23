package com.blankchn.test.feignclient.service;

import com.blankchn.test.feignclient.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author BlankCHN
 * @date 2018-12-21 01:10
 */
@Service
public class HystrixFeignCacheService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(commandKey = "updateUser")
    @CacheResult
    public String updateUser(@CacheKey User user){
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://DATA-PROVIDER/user/update",user,String.class);
        return responseEntity.getBody();
    }

    @HystrixCommand(commandKey = "updateUserAge")
    @CacheResult
    public String updateUserAge(@CacheKey User user){
//        user.setName(user.getName()+"+");
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://DATA-PROVIDER/user/update/other",
                user,String.class);
        return responseEntity.getBody();
    }

    @HystrixCommand(commandKey = "updateUserAge")
    @CacheRemove(commandKey = "updateUserAge")
    public String clearUpdateUserAge(@CacheKey User user){
        System.out.println("清除缓存 age "+user);
        return "清除缓存 age "+user;
    }



    @HystrixCommand(commandKey = "updateUser")
    @CacheRemove(commandKey = "updateUser")
    public String clearUser(@CacheKey User user){
        System.out.println("清除缓存"+user);
        return "清除缓存"+user;
    }

//    public String getKey(User user) {
//        if (user == null) {
//            return "null";
//        }
//        return user.getName();
//    }

}
