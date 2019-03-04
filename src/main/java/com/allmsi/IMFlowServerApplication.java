package com.allmsi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.allmsi.**.dao")
@EnableDiscoveryClient
@EnableFeignClients
@EnableEurekaClient
public class IMFlowServerApplication {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(IMFlowServerApplication.class).web(true).run(args);
		//SpringApplication.run(IMFlowServerApplication.class, args);
	}

}