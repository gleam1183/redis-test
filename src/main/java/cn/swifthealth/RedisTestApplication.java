package cn.swifthealth;

import cn.swifthealth.test.SimpleService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableCaching
@EnableAsync// 开启异步

public class RedisTestApplication {

//    public static void main(String[] args) {
//        ApplicationContext ctx = SpringApplication.run(RedisTestApplication.class, args);
//        SimpleService simpleService = ctx.getBean(SimpleService.class);
//        simpleService.run();
//    }


    public static void main(String[] args) {
        SpringApplication.run(RedisTestApplication.class, args);
    }
}