package com.blankchn.test.feignclient.controller;

import com.blankchn.test.feignclient.model.User;
import com.blankchn.test.feignclient.service.UserFeignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BlankCHN
 * @date 2018-12-14 21:18
 */
@RestController
@RequestMapping("/user")
@Api(description = "展示")
public class UserController {

    @Autowired
    private UserFeignService userFeignService;

    @ApiOperation(value = "增加用户", notes = "新增用户")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String addUser(@RequestBody @ApiParam(name = "用户", value = "传入json格式", required = true)User user){
        return userFeignService.addUser(user);
    }

    @ApiOperation(value = "修改用户", notes = "修改用户")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody @ApiParam(name = "用户", value = "传入json格式", required = true)User user){
        return userFeignService.updateUser(user);
    }

}
