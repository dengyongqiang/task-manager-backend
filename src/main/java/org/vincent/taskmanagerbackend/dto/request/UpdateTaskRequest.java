package org.vincent.taskmanagerbackend.dto.request;

import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record UpdateTaskRequest(
        @Size(max = 200) String title, String description, String status, String priority, LocalDate dueDate) {}
