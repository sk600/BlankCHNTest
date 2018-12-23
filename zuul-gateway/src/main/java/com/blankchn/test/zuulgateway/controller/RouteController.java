package com.blankchn.test.zuulgateway.controller;

import com.blankchn.test.zuulgateway.model.Route;
import com.blankchn.test.zuulgateway.repo.RouteDoRepository;
import com.blankchn.test.zuulgateway.repo.RouteRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BlankCHN
 * @date 2018-12-22 00:42
 */
@Api("动态路由")
@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private RouteDoRepository routeDoRepository;

    @PostMapping("/write")
    @ApiOperation(value = "写入动态路由规则")
    public boolean write(@RequestBody @ApiParam(name = "route", value = "动态路由规则", required = true) Route route){
        if(!routeDoRepository.exists(route)){
            routeRepository.save(route);
            return true;
        }

        return false;
    }

}
