package ru.innop.tasktracker.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innop.tasktracker.models.Project;

import java.text.SimpleDateFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    private String name;
    private String startDate;
    private String completionDate;
    private String status;
    private Integer priority;

    public ProjectDTO(Project project) {
        name = project.getName();
        status = project.getStatus().name();
        priority = project.getPriority();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        startDate = dateFormat.format(project.getStartDate());
        completionDate = dateFormat.format(project.getCompletionDate());
    }
}
