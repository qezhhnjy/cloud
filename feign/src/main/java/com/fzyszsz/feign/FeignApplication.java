package com.fzyszsz.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author fuzy
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@RestController
public class FeignApplication {

	@Resource
	private HelloService helloService;

	public static void main(String[] args) {
		SpringApplication.run(FeignApplication.class, args);
	}

	@GetMapping
	public String hello() {
		return helloService.sayHello();
	}
}
