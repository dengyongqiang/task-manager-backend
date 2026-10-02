package org.vincent.taskmanagerbackend.dto.response;

import java.time.Instant;

public record UserResponse(Long id, String username, String email, Instant createdAt) {}
