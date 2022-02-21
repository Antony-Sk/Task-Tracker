package ru.innop.tasktracker.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.innop.tasktracker.models.Task;
import ru.innop.tasktracker.repositories.TaskRepository;
import ru.innop.tasktracker.services.TaskMangeService;
import ru.innop.tasktracker.transfer.TaskDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskRepository taskRepository;
    private final TaskMangeService taskMangeService;

    public TaskController(TaskRepository taskRepository, TaskMangeService taskMangeService) {
        this.taskRepository = taskRepository;
        this.taskMangeService = taskMangeService;
    }

    @GetMapping
    private List<TaskDTO> getTasksPage() {
        return taskRepository.findAll().stream().map(TaskDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDTO> getTaskPage(@PathVariable("taskId") Long taskId) {
        Optional<Task> taskCandidate = taskRepository.findById(taskId);
        return taskCandidate.map(task -> ResponseEntity.ok(new TaskDTO(task))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createTask(@RequestBody TaskDTO taskDTO) {
        if (taskDTO.getTaskName() == null ||
                taskDTO.getDescription() == null ||
                taskDTO.getPriority() == null ||
                taskDTO.getStatus() == null)
            return ResponseEntity.badRequest().body("Fields shouldn't be equal null");
        if (taskDTO.getId() != null)
            return ResponseEntity.badRequest().body("Id shouldn't be specified");
        taskMangeService.saveTask(taskDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<String> editTask(@RequestBody TaskDTO taskDTO) {
        if (taskDTO.getTaskName() == null ||
                taskDTO.getDescription() == null ||
                taskDTO.getPriority() == null ||
                taskDTO.getStatus() == null)
            return ResponseEntity.badRequest().body("Fields shouldn't be equal null");
        if (taskDTO.getId() == null)
            return ResponseEntity.badRequest().body("Id should be specified");
        taskMangeService.updateTask(taskDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{taskId}")
    private ResponseEntity<String> deleteTask(@PathVariable("taskId") Long taskId) {
        if (!taskRepository.existsById(taskId))
            return ResponseEntity.badRequest().body("Task doesn't exist");
        taskMangeService.deleteTask(taskId);
        return ResponseEntity.ok().build();
    }

}
