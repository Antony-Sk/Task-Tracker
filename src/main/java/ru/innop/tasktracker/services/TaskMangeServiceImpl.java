package ru.innop.tasktracker.services;

import org.springframework.stereotype.Service;
import ru.innop.tasktracker.forms.TaskForm;
import ru.innop.tasktracker.models.Task;
import ru.innop.tasktracker.models.TaskStatus;
import ru.innop.tasktracker.repositories.ProjectRepository;
import ru.innop.tasktracker.repositories.TaskRepository;

@Service
public class TaskMangeServiceImpl implements TaskMangeService {
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    public TaskMangeServiceImpl(ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public void saveTask(TaskForm taskForm, Long projectId) {
        Task task = Task.builder()
                .taskName(taskForm.getTaskName())
                .description(taskForm.getDescription())
                .priority(taskForm.getPriority())
                .status(TaskStatus.valueOf(taskForm.getStatus()))
                .project(projectRepository.getById(projectId))
                .build();
        taskRepository.save(task);
    }

    @Override
    public void updateTask(TaskForm taskForm, Long projectId, Long taskId) {
        Task task = Task.builder()
                .id(taskId)
                .taskName(taskForm.getTaskName())
                .description(taskForm.getDescription())
                .priority(taskForm.getPriority())
                .status(TaskStatus.valueOf(taskForm.getStatus()))
                .project(projectRepository.getById(projectId))
                .build();
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}
