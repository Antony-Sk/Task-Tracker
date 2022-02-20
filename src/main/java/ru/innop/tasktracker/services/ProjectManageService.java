package ru.innop.tasktracker.services;

import ru.innop.tasktracker.forms.ProblemForm;

import java.text.ParseException;

public interface ProjectManageService {
    void deleteProjectById(Long projectId);

    void saveProject(ProblemForm problemForm) throws ParseException;

    void updateProject(ProblemForm problemForm, Long projectId) throws ParseException;
}
