package ru.innop.tasktracker.services;

import ru.innop.tasktracker.forms.TaskForm;

public interface TaskMangeService {
    void saveTask(TaskForm taskForm, Long projectId);

    void updateTask(TaskForm taskForm, Long projectId, Long taskId);

    void deleteTask(Long taskId);
}
