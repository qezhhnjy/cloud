# Spring boot

---

>Spring boot项目启动的三种方式：
- 运行`BootApplication.java`文件
- 项目目录下`mvn spring-boot:run`命令
- `mvn install`将项目打包到target,`java -jar boot.jar`启动

>通过Idea的Spring initialize生成的spring boot项目中的.mvn/mvnw/mvnw.cmd/resources.static/resource.templates都是可以删除的

>而resource/application.properties则是项目的主要配置文件。

>使用嵌入式Tomcat + Thymeleaf模板引擎，并将其作为可执行JAR文件。

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    
对于Thymeleaf模板文件（*.html），放入到templates目录下，对于静态文件放入到static目录中，引入时直接以static为根目录引入。

>Controller的使用

|注解|功能|  
|--|--|  
|`@PathVariable`|获取URL中的数据|  
|`@RequestParam`|获取请求参数的值|  
|`@GetMapping`|组合注解|  
 
    @GetMapping("/girl/{name}")
    public String girl(@PathVariable("name") String name) {
        return girl.toString() + "<br/>" + name;
    //        return content;
    //        return "cupSize : " + cupSize + "<br/> age : " + age;
    }


    @GetMapping({"/say/{id}","/{id}/say"})
    public String say(@PathVariable("id") String id) {
        return "id : " + id;
    }

    //这段代码有个神奇的问题，如果访问/girl/say会发生什么呢？
    
    //会进入/girl路由，不论两个方法书写的先后
=======
# cloud-study

#### 项目介绍
spring cloud 学习项目

#### 软件架构
软件架构说明


#### 安装教程

1. xxxx
2. xxxx
3. xxxx

#### 使用说明

1. xxxx
2. xxxx
3. xxxx

#### 参与贡献

1. Fork 本项目
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request


#### 码云特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. 码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5. 码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6. 码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
