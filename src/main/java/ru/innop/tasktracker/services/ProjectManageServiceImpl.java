package ru.innop.tasktracker.services;

import org.springframework.stereotype.Service;
import ru.innop.tasktracker.forms.ProblemForm;
import ru.innop.tasktracker.models.Project;
import ru.innop.tasktracker.models.ProjectStatus;
import ru.innop.tasktracker.repositories.ProjectRepository;
import ru.innop.tasktracker.repositories.TaskRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        taskRepository.deleteAll(taskRepository.findAllByProjectId(projectId));
        projectRepository.deleteById(projectId);
    }

    @Override
    public void saveProject(ProblemForm problemForm) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        Date startDate = format.parse(problemForm.getStartDate());
        Date completionDate = format.parse(problemForm.getCompletionDate());

        Project project = Project.builder()
                .name(problemForm.getName())
                .startDate(startDate)
                .completionDate(completionDate)
                .status(ProjectStatus.valueOf(problemForm.getStatus()))
                .priority(problemForm.getPriority())
                .build();
        projectRepository.save(project);
    }

    @Override
    public void updateProject(ProblemForm problemForm, Long projectId) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        Date startDate = format.parse(problemForm.getStartDate());
        Date completionDate = format.parse(problemForm.getCompletionDate());

        Project project = Project.builder()
                .id(projectId)
                .name(problemForm.getName())
                .startDate(startDate)
                .completionDate(completionDate)
                .status(ProjectStatus.valueOf(problemForm.getStatus()))
                .priority(problemForm.getPriority())
                .build();
        projectRepository.save(project);
    }
}
