package com.shavlieva.synthetic_human_core_starter.config;

import com.shavlieva.synthetic_human_core_starter.exception.QueueOverflowException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ExecutorConfig {
    private static final int CORE_POOL_SIZE = 2;
    private static final int MAX_POOL_SIZE = 4;
    private static final int QUEUE_CAPACITY = 10;
    private static final long KEEP_ALIVE_TIME = 20;

    @Bean
    public ThreadPoolExecutor taskExecutor() {
        return new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(QUEUE_CAPACITY),
                Executors.defaultThreadFactory(),
                (r, executor) -> {
                    throw new QueueOverflowException("Очередь задач переполнена!");
                }
        );
    }
}
