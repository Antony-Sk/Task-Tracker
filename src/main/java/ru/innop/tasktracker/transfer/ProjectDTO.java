package ru.innop.tasktracker.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innop.tasktracker.models.Project;
import ru.innop.tasktracker.models.ProjectStatus;
import ru.innop.tasktracker.models.Task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    private Long id;
    private String name;
    private Date startDate;
    private Date completionDate;
    private ProjectStatus status;
    private Integer priority;
    private List<Long> tasks;

    public ProjectDTO(Project project) {
        id = project.getId();
        name = project.getName();
        status = project.getStatus();
        priority = project.getPriority();
        startDate = project.getStartDate();
        completionDate = project.getCompletionDate();
        tasks = project.getTasks().stream().map(Task::getId).collect(Collectors.toList());
    }
}
