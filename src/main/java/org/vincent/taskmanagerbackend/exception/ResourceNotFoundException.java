package org.vincent.taskmanagerbackend.exception;

/**
 * @author vincient
 * @create 2026-09-19 16:58
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
