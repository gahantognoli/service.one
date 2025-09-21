package com.gantognoli.service.tasks;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "task")
@Table(name = "task")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String email;
    private LocalDateTime dueDate;
    private boolean notified;

    public TaskEntity(TaskRequest request) {
        this.title = request.title();
        this.email = request.email();
        this.dueDate = request.dueDate();
        this.notified = request.notified();
    }
}
