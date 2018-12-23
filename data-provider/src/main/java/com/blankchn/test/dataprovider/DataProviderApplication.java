package com.blankchn.test.dataprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DataProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataProviderApplication.class, args);
    }

}

