package com.gongnaixiao.susu.redisson;

import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@SpringBootTest
class SpringbootRedissonApplicationTests {

    @Autowired
    private RedissonClient redissonClient;

    @Test
    void contextLoads() {
        redissonClient.getBucket("hello").set("bug");
        String test = (String) redissonClient.getBucket("hello").get();
        System.out.println(test);
    }

    @Test
    public void lockTest() {
        RLock lock = redissonClient.getLock("/lock/all");
        try {
            boolean b = lock.tryLock(10, 10, TimeUnit.SECONDS);
            System.out.println("1111");
            lock.tryLock(1,1,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

}