package com.fzyszsz.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author fuzy
 * create time 18-11-22-下午8:43
 */
@FeignClient("config-client")
public interface HelloService {

    @GetMapping("/")
    String sayHello();
}
