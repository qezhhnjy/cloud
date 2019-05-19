package com.cotek.boot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author qezhhnjy
 */
@Aspect
@Component
public class HttpAspect {

    private Logger log = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.cotek.boot.controller.*Controller.*(..))")
    public void cut() {

    }

    @Before("cut())")
    public void before(JoinPoint point) {
        log.info("test before aspect for spring boot girl controller");

        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = attrs.getRequest();

        //url
        log.info("url={}", req.getRequestURL());

        //method
        log.info("method={}", req.getMethod());

        //ip
        log.info("ip={}", req.getRemoteAddr());

        //类方法
        log.info("classMethod={}", point.getSignature().getDeclaringTypeName() + '.' +
                point.getSignature().getName());

        //参数
        log.info("args={}", point.getArgs());
    }

    @After("cut()")
    public void after() {
        log.info("------test after aspect------");
    }

    @AfterReturning(returning = "obj", pointcut = "cut()")
    public void afterReturn(Object obj) {
        log.info("response={}", obj);
    }
}
