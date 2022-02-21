package ru.innop.tasktracker.services;

import ru.innop.tasktracker.transfer.ProjectDTO;

public interface ProjectManageService {
    void deleteProjectById(Long projectId);

    void saveProject(ProjectDTO problemForm);

    void updateProject(ProjectDTO problemForm);
}
