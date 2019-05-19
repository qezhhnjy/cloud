package com.fzyszsz.redis;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisApplicationTest {

    @Resource
    private RedisTemplate<String, Serializable> template;

    @Test
    public void get() {
        val exec = Executors.newFixedThreadPool(1000);
        template.opsForValue().set("kk", 0);
        IntStream.range(0, 1000).forEach(i -> exec.execute(() -> template.opsForValue().increment("kk", i)));
        val kk = template.opsForValue().get("kk");
        log.info("[字符缓存结果] - {}", kk);
        val key = "qezhhnjy:user:1";
        template.opsForValue().set(key, User.builder().id(9L).username("Tammy").password("123").build());
        val sara = (User) template.opsForValue().get(key);
        log.info("[对象缓存结果]- {} - {}", key, sara);
    }
}