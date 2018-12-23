package com.blankchn.test.feignclient.service;

import com.blankchn.test.feignclient.model.User;

import java.util.concurrent.Future;

/**
 * @author BlankCHN
 * @date 2018-12-21 15:34
 */
public interface IHystrixCollapserService {

    Future<User> collapsing(String name);

    User collapsingSync(String name);

    Future<User> collapsingGlobal(String name);

}
