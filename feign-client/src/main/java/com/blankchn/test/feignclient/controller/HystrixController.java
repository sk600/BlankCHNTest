package com.blankchn.test.feignclient.controller;

import com.blankchn.test.feignclient.model.User;
import com.blankchn.test.feignclient.service.HystrixCollapserService;
import com.blankchn.test.feignclient.service.HystrixFeignCacheService;
import com.blankchn.test.feignclient.service.HystrixFeignService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author BlankCHN
 * @date 2018-12-20 20:18
 */
@Api("hytrix测试使用")
@RestController
@RequestMapping("/hystrix")
public class HystrixController {

    @Autowired
    private HystrixFeignService hystrixFeignService;

    @Autowired
    private HystrixFeignCacheService hystrixFeignCacheService;

    @Autowired
    private HystrixCollapserService hystrixCollapserService;

    @Autowired
    private Random random;

    @HystrixCommand(fallbackMethod = "cir")
    @ApiOperation(value = "通过名称判断是否熔断进行服务降级")
    @GetMapping("/judgement/{name}")
    public String nameCir(@PathVariable("name") @ApiParam(name = "name", value = "名字", required = true) String name) throws Exception {
        System.out.println(name);
        if ("wwt".equals(name)) {
            return "no cir";
        } else {
            throw new Exception();
        }
    }

    @ApiOperation(value = "增加用户", notes = "新增用户")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@RequestBody @ApiParam(name = "用户", value = "传入json格式", required = true) User user) {
        return hystrixFeignService.addUser(user);
    }

    @ApiOperation(value = "修改用户", notes = "修改用户")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody @ApiParam(name = "用户", value = "传入json格式", required = true) User user) {
        return hystrixFeignService.updateUser(user);
    }

    @ApiOperation(value = "修改用户 使用cache", notes = "修改用户")
    @RequestMapping(value = "/cache/update", method = RequestMethod.POST)
    public String updateWithCache(@RequestBody @ApiParam(name = "用户", value = "传入json格式", required = true) User user) {
        hystrixFeignCacheService.updateUser(user);
        hystrixFeignCacheService.updateUser(user);
        return hystrixFeignCacheService.clearUser(user);
    }

    @ApiOperation(value = "修改用户 使用多个key cache", notes = "修改用户")
    @RequestMapping(value = "/cache/update/multipart", method = RequestMethod.POST)
    public String updateWithMultipartCache(@RequestBody @ApiParam(name = "用户", value = "传入json格式", required = true) User user) {
        hystrixFeignCacheService.updateUser(user);
        hystrixFeignCacheService.updateUser(user);
        hystrixFeignCacheService.updateUserAge(user);
        hystrixFeignCacheService.updateUserAge(user);
        hystrixFeignCacheService.clearUser(user);
        hystrixFeignCacheService.updateUserAge(user);
        return hystrixFeignCacheService.clearUpdateUserAge(user);
    }

    @ApiOperation(value = "清除cache")
    @RequestMapping(value = "/cache/update/clear", method = RequestMethod.DELETE)
    public String clearUpdateCache(@RequestBody @ApiParam(name = "用户", value = "传入json格式", required = true) User user) {
        return hystrixFeignCacheService.clearUser(user);
    }

    @ApiOperation(value = "线程内批量修改用户", notes = "使用hystrix collapser")
    @RequestMapping(value = "/cache/update/batch/local", method = RequestMethod.POST)
    public String updateBatchLocal(@RequestBody @ApiParam(name = "用户", value = "传入json格式", required = true) User user) throws ExecutionException, InterruptedException {
        Future<User> user1 = hystrixCollapserService.collapsing(user.getName() + "-" + random.nextInt());
        Future<User> user2 = hystrixCollapserService.collapsing(user.getName() + "-" + random.nextInt());
        Future<User> user3 = hystrixCollapserService.collapsing(user.getName() + "-" + random.nextInt());

        return "" + user1.get() + "\n" + user2.get() + "\n" + user3.get() + "\n";
    }

    @ApiOperation(value = "全局批量修改用户", notes = "使用hystrix collapser")
    @RequestMapping(value = "/cache/update/batch/global", method = RequestMethod.POST)
    public String updateBatchGlobal(@RequestBody @ApiParam(name = "用户", value = "传入json格式", required = true) User user) throws ExecutionException, InterruptedException {
        Future<User> user1 = hystrixCollapserService.collapsingGlobal(user.getName() + "-" + random.nextInt());

        return "" + user1.get() + "\n";
    }

    @ApiOperation(value = "同步批量修改用户", notes = "使用hystrix collapser")
    @RequestMapping(value = "/cache/update/batch/sync", method = RequestMethod.POST)
    public String updateBatchSync(@RequestBody @ApiParam(name = "用户", value = "传入json格式", required = true) User user) throws ExecutionException, InterruptedException {
        StringBuilder sb = new StringBuilder();
        sb.append(hystrixCollapserService.collapsingSync(user.getName() + "-" + random.nextInt()));
        sb.append("\n");
        sb.append(hystrixCollapserService.collapsingSync(user.getName() + "-" + random.nextInt()));
        sb.append("\n");
        sb.append(hystrixCollapserService.collapsingSync(user.getName() + "-" + random.nextInt()));
        sb.append("\n");
        return sb.toString();
    }


    @GetMapping("/getDashboard")
    public List<String> getProviderData() {
        List<String> provider = new ArrayList<String>();
        provider.add("hystrix dashboard");
        return provider;
    }

    public String cir(String name) {
        return name + " cir";
    }

}
