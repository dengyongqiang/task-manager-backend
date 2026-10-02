package org.vincent.taskmanagerbackend.exception;

/**
 * @author vincient
 * @create 2026-09-19 17:12
 */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
