package com.blankchn.test.feignclient.controller;

import com.blankchn.test.feignclient.service.HelloFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author BlankCHN
 * @date 2018-12-14 19:43
 */
@RestController
public class HelloFeignController {

    @Autowired
    private HelloFeignClient helloFeignClient;

    @GetMapping(value = "/search/github")
    public ResponseEntity<byte[]> searchGithubRepoByStr(@RequestParam("str") String queryStr) {
        return helloFeignClient.searchRepo(queryStr);
    }

    @GetMapping(value = "/hello/{name}")
    public String hello(@PathVariable("name") String name, HttpServletRequest request) {
        String lalala = request.getHeader("lalala");
        System.out.println("lalala :" + lalala);
        return "hello " + name + " " + lalala;
    }

}
