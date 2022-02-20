package ru.innop.tasktracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.innop.tasktracker.exceptions.ProjectNotFoundException;
import ru.innop.tasktracker.forms.ProblemForm;
import ru.innop.tasktracker.models.Project;
import ru.innop.tasktracker.repositories.ProjectRepository;
import ru.innop.tasktracker.repositories.TaskRepository;
import ru.innop.tasktracker.services.ProjectManageService;
import ru.innop.tasktracker.transfer.ProjectDTO;

import java.text.SimpleDateFormat;

@Controller
public class ProjectController {
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final ProjectManageService projectManageService;

    public ProjectController(ProjectRepository projectRepository, TaskRepository taskRepository, ProjectManageService projectManageService) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.projectManageService = projectManageService;
    }

    @GetMapping("/")
    public String getIndex(ModelMap modelMap) {
        modelMap.addAttribute("projects", projectRepository.findAll());
        return "index";
    }

    @GetMapping("/project/{projectId}")
    public String getProjectPage(@PathVariable("projectId") Long projectId, ModelMap modelMap) throws ProjectNotFoundException {
        modelMap.addAttribute("project", new ProjectDTO(projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new)));
        modelMap.addAttribute("tasks", taskRepository.findAllByProjectId(projectId));
        return "project";
    }

    @PostMapping("/project/{projectId}")
    public String editProject(ProblemForm problemForm, @PathVariable("projectId") Long projectId, ModelMap modelMap) {
        try {
            projectManageService.updateProject(problemForm, projectId);
        } catch (Exception e) {
            modelMap.addAttribute("errorMessage", "cannot update project");
            return "error";
        }
        return "redirect:/project/" + projectId;
    }

    @PostMapping("/create")
    public String createProject(ProblemForm problemForm, ModelMap modelMap) {
        try {
            projectManageService.saveProject(problemForm);
        } catch (Exception e) {
            modelMap.addAttribute("errorMessage", "cannot create project");
            return "error";
        }
        return "redirect:/";
    }

    @PostMapping("/delete/{projectId}")
    public String deleteProject(@PathVariable("projectId") Long projectId) {
        projectManageService.deleteProjectById(projectId);
        return "redirect:/";
    }
}



