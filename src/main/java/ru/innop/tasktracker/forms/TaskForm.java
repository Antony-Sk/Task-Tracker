package ru.innop.tasktracker.forms;

import lombok.Data;

@Data
public class TaskForm {
    private String taskName;
    private String description;
    private String status;
    private Integer priority;
}
