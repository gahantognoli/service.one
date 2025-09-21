package com.gantognoli.service.tasks;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TaskService {
    private final TasksRepository tasksRepository;
    private final NotificationClient notificationClient;

    public TaskService(TasksRepository tasksRepository, NotificationClient notificationClient) {
        this.tasksRepository = tasksRepository;
        this.notificationClient = notificationClient;
    }

    public void sendNotificationForDueTasks() {
        var deadline = LocalDateTime.now().plusDays(1);
        var dueTasks = tasksRepository.findTasksDueWithinDeadline(deadline);
        for (TaskEntity task : dueTasks) {
            NotificationRequest request = new NotificationRequest(
                "Sua tarefa: " + task.getTitle() + " está prestes a vencer!", task.getEmail());
            notificationClient.sendNotification(request);
            task.setNotified(true);
            tasksRepository.save(task);
        }
    }
}
