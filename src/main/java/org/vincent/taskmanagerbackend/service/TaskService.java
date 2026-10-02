package org.vincent.taskmanagerbackend.service;

import java.util.List;
import org.vincent.taskmanagerbackend.dto.request.CreateTaskRequest;
import org.vincent.taskmanagerbackend.dto.request.UpdateTaskRequest;
import org.vincent.taskmanagerbackend.dto.response.TaskResponse;

/**
 * @author vincient
 * @create 2026-09-19 16:43
 */
public interface TaskService {
    TaskResponse createTask(CreateTaskRequest request, Long userId);

    List<TaskResponse> getTasksByUser(Long userId);

    TaskResponse getTaskById(Long taskId);

    TaskResponse updateTask(Long taskId, UpdateTaskRequest request, Long userId);

    void deleteTask(Long taskId, Long userId);
}
