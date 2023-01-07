package com.labs.ds.todoapp.controller;

import com.labs.ds.todoapp.domain.Task;
import com.labs.ds.todoapp.error.TaskNotFoundException;
import com.labs.ds.todoapp.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/")
    public String index(Model model) {
        List<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "index";
    }

    @PostMapping("/add")
    public String addTask(@RequestParam("title") String title,
                          @RequestParam("description") String description) {
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setDone(false);
        taskRepository.save(task);
        return "redirect:/";
    }

    @GetMapping("/mark-as-done")
    public String markAsDone(@RequestParam("id") Long id) {
        Task task = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
        task.setDone(true);
        taskRepository.save(task);
        return "redirect:/";
    }

    @GetMapping("/mark-as-not-done")
    public String markAsNotDone(@RequestParam("id") Long id) {
        Task task = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
        task.setDone(false);
        taskRepository.save(task);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteTask(@RequestParam("id") Long id) {
        taskRepository.deleteById(id);
        return "redirect:/";
    }
}