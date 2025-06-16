package com.codewithyassine.springrest.service;

import com.codewithyassine.springrest.dto.TaskRequest;
import com.codewithyassine.springrest.dto.TaskResponse;
import com.codewithyassine.springrest.dto.UpdateTaskPriorityRequest;
import com.codewithyassine.springrest.dto.UpdateTaskStatusRequest;
import com.codewithyassine.springrest.exception.TaskNotFoundException;
import com.codewithyassine.springrest.model.Task;
import com.codewithyassine.springrest.model.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import com.codewithyassine.springrest.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Override
    public TaskResponse createTask(TaskRequest request){

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setDueDate(request.getDueDate());
        task.setPriority(request.getPriority());
        if(request.getStatus() != null){
            task.setStatus(request.getStatus());
        }else{
            task.setStatus(TaskStatus.PENDING);
        }
        Task savedTask = taskRepository.save(task);

        TaskResponse response = new TaskResponse();
        response.setId(savedTask.getId());
        response.setTitle(savedTask.getTitle());
        response.setDescription(savedTask.getDescription());
        response.setDueDate(savedTask.getDueDate());
        response.setStatus(savedTask.getStatus());
        response.setPriority(savedTask.getPriority());
        return response;

    }
    
    public List<TaskResponse> getAllTasks(){
        List<Task> tasks = taskRepository.findAll();
        List<TaskResponse> taskResponses = new ArrayList<>();
        for(Task task : tasks){
            TaskResponse taskResponse = new TaskResponse();
            taskResponse.setId(task.getId());
            taskResponse.setTitle(task.getTitle());
            taskResponse.setDescription(task.getDescription());
            taskResponse.setDueDate(task.getDueDate());
            taskResponse.setStatus(task.getStatus());
            taskResponse.setPriority(task.getPriority());
            taskResponses.add(taskResponse);
        }
        return taskResponses;
    };

    public TaskResponse getTaskById(Long id){
        Task task=taskRepository.findById(id).orElse(null);
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(task.getId());
        taskResponse.setTitle(task.getTitle());
        taskResponse.setDescription(task.getDescription());
        taskResponse.setDueDate(task.getDueDate());
        taskResponse.setStatus(task.getStatus());
        taskResponse.setPriority(task.getPriority());
        return taskResponse;
    }
    public List<TaskResponse> getTasksByStatus(TaskStatus status){
        List<Task> tasks = taskRepository.findByStatus(status);
        List<TaskResponse> taskResponses = new ArrayList<>();
        for(Task task : tasks){
            TaskResponse taskResponse = new TaskResponse();
            taskResponse.setId(task.getId());
            taskResponse.setTitle(task.getTitle());
            taskResponse.setDescription(task.getDescription());
            taskResponse.setDueDate(task.getDueDate());
            taskResponse.setStatus(task.getStatus());
            taskResponse.setPriority(task.getPriority());
            taskResponses.add(taskResponse);
        }
        return taskResponses;
    }

    public TaskResponse updateTaskStatus(Long id, UpdateTaskStatusRequest status){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with ID " + id + " not found"));
        task.setStatus(status.getStatus());
        Task updatedTask = taskRepository.save(task);

        TaskResponse response = new TaskResponse();
        response.setId(updatedTask.getId());
        response.setTitle(updatedTask.getTitle());
        response.setDescription(updatedTask.getDescription());
        response.setDueDate(updatedTask.getDueDate());
        response.setStatus(updatedTask.getStatus());
        response.setPriority(updatedTask.getPriority());

        return response;
    }

    public TaskResponse deleteTask(Long id){
        Task task = taskRepository.findById(id).orElse(null);
        taskRepository.delete(task);
        return null;
    }
    public TaskResponse updateTaskPriority(Long id, UpdateTaskPriorityRequest request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id " + id));
        task.setPriority(request.getPriority());
        Task updated = taskRepository.save(task);
        return mapToResponse(updated);
    }
    private TaskResponse mapToResponse(Task task) {
        TaskResponse response = new TaskResponse();
        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setDueDate(task.getDueDate());
        response.setStatus(task.getStatus());
        response.setPriority(task.getPriority());
        return response;
    }


}
