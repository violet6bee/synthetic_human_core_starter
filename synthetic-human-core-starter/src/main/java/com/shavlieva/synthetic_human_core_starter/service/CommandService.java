package com.shavlieva.synthetic_human_core_starter.service;

import com.shavlieva.synthetic_human_core_starter.DTO.Command;
import com.shavlieva.synthetic_human_core_starter.aop.WeylandWatchingYou;
import com.shavlieva.synthetic_human_core_starter.enums.Priority;
import com.shavlieva.synthetic_human_core_starter.exception.QueueOverflowException;
import com.shavlieva.synthetic_human_core_starter.metrics.MetricsService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadPoolExecutor;

@Service
@RequiredArgsConstructor
public class CommandService {
    private static final Logger log = LoggerFactory.getLogger(CommandService.class);
    private final ThreadPoolExecutor executor;
    private final MetricsService metricsService;

    @WeylandWatchingYou
    public void processCommand(Command command) {
        if (command.getPriority() == Priority.CRITICAL) {
            log.info("Выполняется CRITICAL команда: {}", command);
            metricsService.incrementTasksForAuthor(command.getAuthor());
        } else {
            if (executor.getQueue().remainingCapacity() == 0) {
                throw new QueueOverflowException("Очередь задач переполнена");
            }
            executor.execute(() -> {
                log.info("Выполняется COMMON команда: {}", command);
                metricsService.incrementTasksForAuthor(command.getAuthor());
                metricsService.updateQueueSize(executor.getQueue().size());
            });
            metricsService.updateQueueSize(executor.getQueue().size());
        }
    }
}
