package ru.innop.tasktracker.services;


import ru.innop.tasktracker.transfer.TaskDTO;

public interface TaskMangeService {
    void saveTask(TaskDTO taskDTO);

    void updateTask(TaskDTO taskDTO);

    void deleteTask(Long taskId);
}
