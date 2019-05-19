package com.fzyszsz.study.first;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author fuzy
 * create time 19-3-8-下午9:30
 */
@Slf4j
public class MyClassLoader {

    public static void main(String[] args) {
        ClassLoader classLoader = MyClassLoader.class.getClassLoader();
        log.info("ClassLoader : {}", classLoader);
        log.info("parent : {}", classLoader.getParent());
        log.info("parent's parent : {}", classLoader.getParent().getParent());
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(ClassLoader.getSystemClassLoader());
    }
}
