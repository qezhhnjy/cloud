package com.fzyszsz.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fuzy
 */
@SpringBootApplication
@RestController
@EnableEurekaClient
// 配置用于git配置文件修改时自动刷新
@RefreshScope
public class ConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }

    @Value("${hello}")
    private String hello;

    @Value("${server.port}")
    private String port;

    @RequestMapping(value = "/hello")
    public String hello() {
        return hello + port;
    }

    @RequestMapping(value = "/")
    public String index() {
        return "Hello World From " + port;
    }
}
