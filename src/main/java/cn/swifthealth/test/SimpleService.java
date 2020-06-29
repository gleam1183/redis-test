package cn.swifthealth.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class SimpleService {
    static Logger logger = LoggerFactory.getLogger(SimpleService.class);

    @Autowired
    RedisTemplate redisTemplate;

    public void run() {
        try {
            logger.info("redis连接工厂：{}", redisTemplate.getConnectionFactory());
            //添加key
            redisTemplate.opsForValue().set("user", "张三");
            //获取key
            logger.info("从redis中获取key=user的值为：{}", redisTemplate.opsForValue().get("user"));
            //删除key
            redisTemplate.delete("user");

            //RedisTemplate中定义了对5种数据结构操作
            redisTemplate.opsForValue();//操作字符串
            redisTemplate.opsForHash();//操作hash
            redisTemplate.opsForList();//操作list
            redisTemplate.opsForSet();//操作set
            redisTemplate.opsForZSet();//操作有序set
            redisTemplate.opsForList();

            //参考文档
            //https://docs.spring.io/spring-data/redis/docs/2.3.0.RELEASE/reference/html/#reference

            //设置超时时间
            redisTemplate.opsForValue().set("user", "lfr is in redis", 3, TimeUnit.SECONDS);
            logger.info("【redis超时时间测试】【初始化】【超时时间=5s】从redis中获取key=user的值为：{}", redisTemplate.opsForValue().get("user"));
            Thread.sleep(2000);
            logger.info("【redis超时时间测试】【第2秒获取】从redis中获取key=user的值为：{}", redisTemplate.opsForValue().get("user"));
            Thread.sleep(1000);
            logger.info("【redis超时时间测试】【第3秒获取】从redis中获取key=user的值为：{}", redisTemplate.opsForValue().get("user"));
            Thread.sleep(1000);
            logger.info("【redis超时时间测试】【第4秒获取】从redis中获取key=user的值为：{}", redisTemplate.opsForValue().get("user"));

            redisTemplate.opsForValue().set("user", "lfr is coming");

            logger.info("【redis超时时间测试】【第5秒获取】从redis中获取key=user的值为：{}", redisTemplate.opsForValue().getAndSet("user", "lfr is leaving"));

            Thread.sleep(1000);

            redisTemplate.opsForValue().set("user", "lfr is in redis", 1, TimeUnit.SECONDS);
            redisTemplate.opsForValue().setIfAbsent("user","111");

            logger.info("【redis超时时间测试】【第5秒获取】从redis中获取key=user的值为：{}", redisTemplate.opsForValue().get("user"));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
