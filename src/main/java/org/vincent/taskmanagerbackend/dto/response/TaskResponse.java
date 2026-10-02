package org.vincent.taskmanagerbackend.dto.response;

import java.time.Instant;
import java.time.LocalDate;

public record TaskResponse(
        Long id,
        String title,
        String description,
        String status,
        String priority,
        Long userId,
        LocalDate dueDate,
        Instant createdAt,
        Instant updatedAt) {}
