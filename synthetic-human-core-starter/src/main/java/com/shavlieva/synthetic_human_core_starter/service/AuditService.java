package com.shavlieva.synthetic_human_core_starter.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class AuditService {
    private static final Logger log = LoggerFactory.getLogger(AuditService.class);
    private final KafkaTemplate<String, String> kafkaTemplate;
    private boolean sendToKafka = false;
    public void audit(String methodName, Object[] args, Object result) {
        String message = String.format("Method: %s, Args: %s, Result: %s", methodName, Arrays.toString(args), result);
        if (sendToKafka) {
            kafkaTemplate.send("audit-topic", message);
        } else {
            log.info(message);
        }
    }
}
