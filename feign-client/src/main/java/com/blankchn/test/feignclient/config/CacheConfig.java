package com.blankchn.test.feignclient.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.Controller;

/**
 * @author BlankCHN
 * @date 2018-12-21 00:32
 */
@Configuration
public class CacheConfig {

    @Bean
    @ConditionalOnClass(Controller.class)
    public CacheContextInterceptor userContextInterceptor(){
        return new CacheContextInterceptor();
    }

    @Configuration
    @ConditionalOnClass(Controller.class)
    public class WebMvcConfig implements WebMvcConfigurer {
        @Autowired
        CacheContextInterceptor userContextInterceptor;

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(userContextInterceptor);
        }
    }

}
