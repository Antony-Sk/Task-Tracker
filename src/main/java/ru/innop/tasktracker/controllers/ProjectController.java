package ru.innop.tasktracker.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innop.tasktracker.models.Project;
import ru.innop.tasktracker.repositories.ProjectRepository;
import ru.innop.tasktracker.repositories.TaskRepository;
import ru.innop.tasktracker.services.ProjectManageService;
import ru.innop.tasktracker.transfer.ProjectDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectRepository projectRepository;
    private final ProjectManageService projectManageService;
    private final TaskRepository taskRepository;

    public ProjectController(ProjectRepository projectRepository, ProjectManageService projectManageService, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.projectManageService = projectManageService;
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public List<ProjectDTO> getAllProjectsPage() {
        return projectRepository.findAll().stream().map(ProjectDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDTO> getProjectPage(@PathVariable("projectId") Long projectId) {
        Optional<Project> projectCandidate = projectRepository.findById(projectId);
        return projectCandidate.map(project -> ResponseEntity.ok(new ProjectDTO(project))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createProject(@RequestBody ProjectDTO projectDTO) {
        if (projectDTO.getName() == null ||
                projectDTO.getPriority() == null ||
                projectDTO.getCompletionDate() == null ||
                projectDTO.getStartDate() == null ||
                projectDTO.getStatus() == null)
            return ResponseEntity.badRequest().body("Fields shouldn't be equal null");
        if (projectDTO.getId() != null)
            return ResponseEntity.badRequest().body("Id should be equal null");
        for (Long taskId : projectDTO.getTasks()) {
            if (!taskRepository.existsById(taskId)) {
                return ResponseEntity.badRequest().body("Tasks do not exist");
            }
        }
        projectManageService.saveProject(projectDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<String> updateProject(@RequestBody ProjectDTO projectDTO) {
        if (projectDTO.getName() == null ||
                projectDTO.getPriority() == null ||
                projectDTO.getCompletionDate() == null ||
                projectDTO.getStartDate() == null ||
                projectDTO.getStatus() == null)
            return ResponseEntity.badRequest().body("Fields shouldn't be equal null");
        if (projectDTO.getId() == null)
            return ResponseEntity.badRequest().body("Id shouldn't be equal null");
        if (!projectRepository.existsById(projectDTO.getId()))
            return ResponseEntity.badRequest().body("Project doesn't exist");
        for (Long taskId : projectDTO.getTasks()) {
            if (!taskRepository.existsById(taskId)) {
                return ResponseEntity.badRequest().body("Tasks do not exist");
            }
        }
        projectManageService.updateProject(projectDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<String> deleteProject(@PathVariable Long projectId) {
        if (projectRepository.findById(projectId).isPresent())
            projectManageService.deleteProjectById(projectId);
        else
            return ResponseEntity.badRequest().body("Project not found");
        return ResponseEntity.ok().build();
    }
}



