package ru.innop.tasktracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.innop.tasktracker.forms.TaskForm;
import ru.innop.tasktracker.repositories.TaskRepository;
import ru.innop.tasktracker.services.TaskMangeService;

@Controller
public class TaskController {
    private final TaskRepository taskRepository;
    private final TaskMangeService taskMangeService;

    public TaskController(TaskRepository taskRepository, TaskMangeService taskMangeService) {
        this.taskRepository = taskRepository;
        this.taskMangeService = taskMangeService;
    }

    @GetMapping("/project/{projectId}/task/{taskId}")
    public String getTaskPage(@PathVariable("projectId") Long projectId, @PathVariable("taskId") Long taskId, ModelMap modelMap) {
        modelMap.addAttribute("task", taskRepository.findById(taskId).orElseThrow());
        return "task";
    }

    @PostMapping("/project/{projectId}/task/{taskId}/edit")
    public String editTask(@PathVariable("projectId") Long projectId, @PathVariable("taskId") Long taskId, TaskForm taskForm, ModelMap modelMap) {
        try {
            taskMangeService.updateTask(taskForm, projectId, taskId);
        } catch (Exception e) {
            modelMap.addAttribute("errorMessage", "Cannot create a task!!!");
            return "error";
        }
        return "redirect:/project/" + projectId + "/task/" + taskId;
    }

    @PostMapping("project/{projectId}/createTask")
    public String createTask(TaskForm taskForm, @PathVariable Long projectId, ModelMap modelMap) {
        try {
            taskMangeService.saveTask(taskForm, projectId);
        } catch (Exception e) {
            modelMap.addAttribute("errorMessage", "Cannot create a task!!!");
            return "error";
        }
        return "redirect:/project/" + projectId;
    }

    @PostMapping("/project/{projectId}/task/{taskId}/delete")
    private String deleteTask(@PathVariable("taskId") Long taskId, @PathVariable("projectId") Long projectId) {
        taskMangeService.deleteTask(taskId);
        return "redirect:/project/" + projectId;
    }

}
