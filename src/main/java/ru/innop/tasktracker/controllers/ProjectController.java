package ru.innop.tasktracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.innop.tasktracker.models.ProjectStatus;
import ru.innop.tasktracker.repositories.ProjectRepository;

@Controller
public class IndexController {
    private final ProjectRepository projectRepository;

    public IndexController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping("/")
    public String getIndex(ModelMap modelMap) {
        modelMap.addAttribute("projects", projectRepository.findAll());
        return "index";
    }
}

