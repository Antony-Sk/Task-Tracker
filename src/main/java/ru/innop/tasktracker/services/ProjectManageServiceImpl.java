package ru.innop.tasktracker.services;

import org.springframework.stereotype.Service;
import ru.innop.tasktracker.models.Project;
import ru.innop.tasktracker.repositories.ProjectRepository;
import ru.innop.tasktracker.repositories.TaskRepository;
import ru.innop.tasktracker.transfer.ProjectDTO;

import java.util.stream.Collectors;

@Service
public class ProjectManageServiceImpl implements ProjectManageService {
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    public ProjectManageServiceImpl(ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public void deleteProjectById(Long projectId) {
        projectRepository.deleteById(projectId);
    }

    @Override
    public void saveProject(ProjectDTO projectDTO) {
        Project project = Project.builder()
                .name(projectDTO.getName())
                .startDate(projectDTO.getStartDate())
                .completionDate(projectDTO.getCompletionDate())
                .status(projectDTO.getStatus())
                .priority(projectDTO.getPriority())
                .tasks(projectDTO.getTasks().stream().map(taskId -> taskRepository.findById(taskId).get()).collect(Collectors.toList()))
                .build();
        projectRepository.save(project);
    }

    @Override
    public void updateProject(ProjectDTO projectDTO) {
        Project project = Project.builder()
                .id(projectDTO.getId())
                .name(projectDTO.getName())
                .startDate(projectDTO.getStartDate())
                .completionDate(projectDTO.getCompletionDate())
                .status(projectDTO.getStatus())
                .priority(projectDTO.getPriority())
                .tasks(projectDTO.getTasks().stream().map(taskId -> taskRepository.findById(taskId).get()).collect(Collectors.toList()))
                .build();
        projectRepository.save(project);
    }
}
