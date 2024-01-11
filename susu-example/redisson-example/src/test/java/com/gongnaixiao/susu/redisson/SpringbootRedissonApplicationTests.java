package com.gongnaixiao.susu.redisson;

import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@SpringBootTest
class SpringbootRedissonApplicationTests {

    @Autowired
    private RedissonClient redissonClient;

    @Qualifier("newThreadPoolTaskExecutor")
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Test
    void contextLoads() {
        redissonClient.getBucket("hello").set("bug");
        String test = (String) redissonClient.getBucket("hello").get();
        System.out.println(test);
    }

    @Test
    public void lockTest() {
        for (int i = 0; i < 10; i++)
            taskExecutor.execute(() -> {
                RLock lock = redissonClient.getLock("/lock/all");
                try {
                    boolean b = lock.tryLock(10, 10, TimeUnit.SECONDS);
                    if (!b) {
                        System.out.println("try to lock timeout");
                        throw new RuntimeException("try to lock timeout");
                    }
                    System.out.println("1111");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            });
    }

}