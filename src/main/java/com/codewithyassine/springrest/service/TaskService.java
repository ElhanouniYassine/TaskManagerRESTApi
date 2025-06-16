package com.codewithyassine.springrest.service;

import com.codewithyassine.springrest.dto.TaskRequest;
import com.codewithyassine.springrest.dto.TaskResponse;
import com.codewithyassine.springrest.dto.UpdateTaskPriorityRequest;
import com.codewithyassine.springrest.dto.UpdateTaskStatusRequest;
import com.codewithyassine.springrest.model.TaskStatus;
import java.util.List;

public interface TaskService {

    TaskResponse createTask(TaskRequest request);
    List<TaskResponse> getAllTasks();
    TaskResponse getTaskById(Long id);
    List<TaskResponse> getTasksByStatus(TaskStatus status);
    TaskResponse updateTaskStatus(Long id, UpdateTaskStatusRequest request);
    TaskResponse updateTaskPriority(Long id, UpdateTaskPriorityRequest priority);
    TaskResponse deleteTask(Long id);
}
