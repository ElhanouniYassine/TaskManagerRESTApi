package com.codewithyassine.springrest.controller;
import com.codewithyassine.springrest.dto.TaskRequest;
import com.codewithyassine.springrest.dto.TaskResponse;
import com.codewithyassine.springrest.dto.UpdateTaskPriorityRequest;
import com.codewithyassine.springrest.dto.UpdateTaskStatusRequest;
import com.codewithyassine.springrest.model.TaskPriority;
import com.codewithyassine.springrest.model.TaskStatus;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.codewithyassine.springrest.service.TaskService;
import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/tasks")
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest request) {
        TaskResponse createdTask = taskService.createTask(request);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskResponse>> getTasks() {
        List<TaskResponse> allTasks = taskService.getAllTasks();
        return new ResponseEntity<>(allTasks, HttpStatus.OK);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable Long id) {
        TaskResponse task = taskService.getTaskById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping("/tasks/{status}")
    public ResponseEntity<TaskResponse> getTaskByStatus(@PathVariable String status) {
        TaskResponse task= (TaskResponse) taskService.getTasksByStatus(TaskStatus.valueOf(status));
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/tasks/{id}/status")
    public ResponseEntity<TaskResponse> updateTaskStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTaskStatusRequest request) {

        TaskResponse updatedTask = taskService.updateTaskStatus(id, request);
        return ResponseEntity.ok(updatedTask);
    }

    @PutMapping("tasks/{id}/priority")
    public ResponseEntity<TaskResponse> updateTaskPriority(
            @PathVariable Long id,
            @RequestBody UpdateTaskPriorityRequest request
    ) {
        TaskResponse updated = taskService.updateTaskPriority(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<TaskResponse> deleteTask(@PathVariable Long id) {
        TaskResponse deletedTask=taskService.deleteTask(id);
        return new ResponseEntity<>(deletedTask, HttpStatus.OK);

    }

    @GetMapping("tasks/priority/{priority}")
    public ResponseEntity<List<TaskResponse>> getTasksByPriority(@PathVariable String priority) {
        List<TaskResponse> tasks = taskService.getTasksByPriority(TaskPriority.valueOf(priority.toUpperCase()));
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("tasks/statusAndPriority/{priority}/{status}")
    public ResponseEntity<List<TaskResponse>> getTasksByStatusAndPriority(@PathVariable String priority, @PathVariable String status) {
        List<TaskResponse> tasks=taskService.getTasksByStatusAndPriority(TaskStatus.valueOf(status.toUpperCase()), TaskPriority.valueOf(priority.toUpperCase()));
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }


}
