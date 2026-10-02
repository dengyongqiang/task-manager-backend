package org.vincent.taskmanagerbackend.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vincent.taskmanagerbackend.dto.request.CreateTaskRequest;
import org.vincent.taskmanagerbackend.dto.request.UpdateTaskRequest;
import org.vincent.taskmanagerbackend.dto.response.TaskResponse;
import org.vincent.taskmanagerbackend.entity.Task;
import org.vincent.taskmanagerbackend.entity.User;
import org.vincent.taskmanagerbackend.exception.ResourceNotFoundException;
import org.vincent.taskmanagerbackend.exception.UnauthorizedException;
import org.vincent.taskmanagerbackend.repository.TaskRepository;
import org.vincent.taskmanagerbackend.repository.UserRepository;
import org.vincent.taskmanagerbackend.service.TaskService;

/**
 * @author vincient
 * @create 2026-09-19 16:43
 */
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public TaskResponse createTask(CreateTaskRequest request, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        Task task = new Task();
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setPriority(request.priority());
        task.setStatus("TODO");
        task.setDueDate(request.dueDate());
        task.setUser(user);
        Task saved = taskRepository.save(task);
        return toResponse(saved);
    }

    @Override
    public List<TaskResponse> getTasksByUser(Long userId) {
        return taskRepository.findByUserId(userId).stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public TaskResponse getTaskById(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        return toResponse(task);
    }

    @Override
    public TaskResponse updateTask(Long taskId, UpdateTaskRequest request, Long userId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        if (!task.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Not your task");
        }
        if (request.title() != null) task.setTitle(request.title());
        if (request.description() != null) task.setDescription(request.description());
        if (request.status() != null) task.setStatus(request.status());
        if (request.priority() != null) task.setPriority(request.priority());
        if (request.dueDate() != null) task.setDueDate(request.dueDate());
        return toResponse(taskRepository.save(task));
    }

    @Override
    public void deleteTask(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        if (!task.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Not your task");
        }
        taskRepository.delete(task);
    }

    private TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getUser().getId(),
                task.getDueDate(),
                task.getCreatedAt(),
                task.getUpdatedAt());
    }
}
