package org.vincent.taskmanagerbackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "tasks", schema = "task_manager")
public class Task extends BaseEntity {

    @Size(max = 200)
    @NotNull
    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Lob
    @Column(name = "description")
    private String description;

    @ColumnDefault("'TODO'")
    @Column(name = "status", length = 20)
    private String status;

    @ColumnDefault("'MEDIUM'")
    @Column(name = "priority", length = 20)
    private String priority;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(name = "due_date")
    private LocalDate dueDate;
}
