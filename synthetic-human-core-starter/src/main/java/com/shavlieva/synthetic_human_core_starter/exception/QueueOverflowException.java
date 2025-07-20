package com.shavlieva.synthetic_human_core_starter.exception;

public class QueueOverflowException extends RuntimeException {
    public QueueOverflowException(String message) {
        super(message);
    }
}
