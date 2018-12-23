package com.blankchn.test.feignclient.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author BlankCHN
 * @date 2018-12-20 17:20
 */
@Api(value = "ribbon测试使用")
@RestController
@RequestMapping("/ribbon")
public class RibbonController {

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation(value = "获取用户标签", notes = "ribbon测试")
    @RequestMapping(value = "/{name}/{year}", method = RequestMethod.GET)
    public String getUserTag(@PathVariable("name") @ApiParam(value = "姓名",name = "name", required = true) String name,
                       @PathVariable("year") @ApiParam(value = "年龄",name = "year", required = true) String year){
        System.out.println(name+":"+year);
        String url = "http://DATA-PROVIDER/user/tag/"+name+"/"+year;
        return restTemplate.getForObject(url,String.class);
    }

}
