package org.vincent.taskmanagerbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.vincent.taskmanagerbackend.dto.request.CreateTaskRequest;
import org.vincent.taskmanagerbackend.dto.request.UpdateTaskRequest;
import org.vincent.taskmanagerbackend.dto.response.ApiResponse;
import org.vincent.taskmanagerbackend.dto.response.TaskResponse;
import org.vincent.taskmanagerbackend.service.TaskService;

import java.util.List;

/**
 * @author vincient
 * @create 2026-09-19 17:33
 */
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private static final Long CURRENT_USER_ID = 1L;

    @PostMapping
    public ApiResponse<TaskResponse> createTask(@Valid @RequestBody CreateTaskRequest request) {
        TaskResponse result = taskService.createTask(request, CURRENT_USER_ID);
        return ApiResponse.ok(result);
    }

    @GetMapping
    public ApiResponse<List<TaskResponse>> getTasks() {
        List<TaskResponse> tasks = taskService.getTasksByUser(CURRENT_USER_ID);
        return ApiResponse.ok(tasks);
    }

    @GetMapping("/{id}")
    public ApiResponse<TaskResponse> getTask(@PathVariable Long id) {
        TaskResponse task = taskService.getTaskById(id);
        return ApiResponse.ok(task);
    }

    @PutMapping("/{id}")
    public ApiResponse<TaskResponse> updateTask(@PathVariable Long id, @Valid @RequestBody UpdateTaskRequest request) {
        TaskResponse result = taskService.updateTask(id, request, CURRENT_USER_ID);
        return ApiResponse.ok(result);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id, CURRENT_USER_ID);
        return ApiResponse.ok();
    }
}
