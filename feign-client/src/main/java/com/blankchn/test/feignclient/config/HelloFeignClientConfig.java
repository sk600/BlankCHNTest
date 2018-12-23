package com.blankchn.test.feignclient.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author BlankCHN
 * @date 2018-12-14 19:46
 */
@Configuration
public class HelloFeignClientConfig {

    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

}
