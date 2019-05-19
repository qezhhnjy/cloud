package com.fzyszsz.ribbon;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author fuzy
 * create time 18-11-22-下午6:28
 */
@Service
public class HelloService {

    @Resource
    private RestTemplate restTemplate;

    public String getHelloContent() {
        return restTemplate.getForObject("http://config-client/hello", String.class);
    }

}
