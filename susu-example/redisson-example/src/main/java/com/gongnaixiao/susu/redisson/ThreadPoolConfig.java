package com.gongnaixiao.susu.redisson;

import org.springframework.boot.task.TaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.time.Duration;

@Configuration
public class ThreadPoolConfig {
    @Bean
    public ThreadPoolTaskExecutor newThreadPoolTaskExecutor() {
        return new TaskExecutorBuilder()
                .corePoolSize(5)
                .maxPoolSize(10)
                .queueCapacity(5)
                .keepAlive(Duration.ofSeconds(60))
                .threadNamePrefix("redisson-pool-")
                .awaitTermination(true)
                .build();
    }
}
