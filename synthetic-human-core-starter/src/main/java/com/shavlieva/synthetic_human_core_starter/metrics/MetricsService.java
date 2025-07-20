package com.shavlieva.synthetic_human_core_starter.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MetricsService {
    private final AtomicInteger queueSize;
    private final ConcurrentHashMap<String, AtomicInteger> authorTasks;

    public MetricsService(MeterRegistry registry) {
        this.queueSize = registry.gauge("bishop.queue.size", new AtomicInteger(0));
        this.authorTasks = new ConcurrentHashMap<>();
    }

    public void updateQueueSize(int size) {
        this.queueSize.set(size);
    }

    public void incrementTasksForAuthor(String author) {
        authorTasks.computeIfAbsent(author, a -> new AtomicInteger(0)).incrementAndGet();
    }
}
