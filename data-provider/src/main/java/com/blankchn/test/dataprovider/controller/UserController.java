package com.blankchn.test.dataprovider.controller;

import com.blankchn.test.dataprovider.model.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author BlankCHN
 * @date 2018-12-14 20:55
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUser(User user, HttpServletRequest request){
        String token = request.getHeader("oauthToken");
        System.out.println(user.getName());
        return "hello,"+user.getName()+":"+token+"："+request.getRequestURL();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateUser(@RequestBody User user){
        System.out.println(user.getName());
        return "hello,"+user.getName();
    }

    @RequestMapping(value = "/update/other", method = RequestMethod.POST)
    public String updateUserOther(@RequestBody User user){
        System.out.println(user.getName());
        return "hello,"+user.getName();
    }

    @RequestMapping(value = "/update/batch", method = RequestMethod.POST)
    public List<User> updateUserBatch(@RequestBody List<User> users){
        System.out.println("共有"+users.size()+"个user");
        users.forEach(user -> {
            user.setName(user.getName()+" 已更改");
        });
        return users;
    }

    @GetMapping("/tag/{name}/{year}")
    public String getUserTag(@PathVariable("name")String name,
                             @PathVariable("year")String year){
        System.out.println(name+":"+year);
        return name+"-----"+year;
    }

}
