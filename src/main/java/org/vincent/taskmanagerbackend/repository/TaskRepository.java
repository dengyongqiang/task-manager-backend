package org.vincent.taskmanagerbackend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.vincent.taskmanagerbackend.entity.Task;

/**
 * @author vincient
 * @create 2026-09-13 22:19
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findByUserIdAndStatus(Long userId, String status);

    List<Task> findByUserId(Long userId);
}
