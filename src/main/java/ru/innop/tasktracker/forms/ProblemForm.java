package ru.innop.tasktracker.forms;

import lombok.Data;

@Data
public class ProblemForm {
    private String name;
    private String startDate;
    private String completionDate;
    private String status;
    private Integer priority;
}
