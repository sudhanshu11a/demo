package org.sudhanshu.demo.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class DemoGatewayApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoGatewayApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoGatewayApplication.class, args);
    }
}
