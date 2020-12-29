package org.sudhanshu.demo.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DemoEurekaApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(DemoEurekaApplication.class);
		application.setBanner(new CustomBanner());
		application.run(args);
		//SpringApplication.run(DemoEurekaApplication.class, args);
	}

}
