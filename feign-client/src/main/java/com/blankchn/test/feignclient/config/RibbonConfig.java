package com.blankchn.test.feignclient.config;

import com.blankchn.test.feignclient.anno.AvoidScan;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author BlankCHN
 * @date 2018-12-20 19:07
 */
@Configuration
@AvoidScan
public class RibbonConfig {


    @Bean
    public IRule ribbionRule(){
        return new RandomRule();
    }

}
