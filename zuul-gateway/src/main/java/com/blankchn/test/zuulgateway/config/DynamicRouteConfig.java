package com.blankchn.test.zuulgateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author BlankCHN
 * @date 2018-12-22 01:10
 */
@Configuration
public class DynamicRouteConfig {

    @Autowired
    private ZuulProperties zuulProperties;

    @Autowired
    private ServerProperties server;

    @Bean
    public DynamicRouteLocator dynamicRouteLocator(){
        return new DynamicRouteLocator(server.getServlet().getContextPath(),zuulProperties);
    }

}
