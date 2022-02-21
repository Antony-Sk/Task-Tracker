package ru.innop.tasktracker.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innop.tasktracker.models.Task;
import ru.innop.tasktracker.models.TaskStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private Long id;

    private String taskName;

    private String description;

    private Integer priority;

    private TaskStatus status;

    public TaskDTO(Task task) {
        id = task.getId();
        taskName = task.getTaskName();
        description = task.getDescription();
        priority = task.getPriority();
        status = task.getStatus();
    }
}
