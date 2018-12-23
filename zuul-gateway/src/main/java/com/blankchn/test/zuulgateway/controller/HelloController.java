package com.blankchn.test.zuulgateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BlankCHN
 * @date 2018-12-21 21:59
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/{name}")
    public String hello(@PathVariable("name")String name){
        return "hello "+name;
    }

}
