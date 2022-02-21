package ru.innop.tasktracker.services;

import org.springframework.stereotype.Service;
import ru.innop.tasktracker.models.Task;
import ru.innop.tasktracker.repositories.TaskRepository;
import ru.innop.tasktracker.transfer.TaskDTO;

@Service
public class TaskMangeServiceImpl implements TaskMangeService {
    private final TaskRepository taskRepository;

    public TaskMangeServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void saveTask(TaskDTO taskDTO) {
        Task task = Task.builder()
                .taskName(taskDTO.getTaskName())
                .description(taskDTO.getDescription())
                .priority(taskDTO.getPriority())
                .status(taskDTO.getStatus())
                .build();
        taskRepository.save(task);
    }

    @Override
    public void updateTask(TaskDTO taskDTO) {
        Task task = Task.builder()
                .id(taskDTO.getId())
                .taskName(taskDTO.getTaskName())
                .description(taskDTO.getDescription())
                .priority(taskDTO.getPriority())
                .status(taskDTO.getStatus())
                .build();
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}
