package com.codewithyassine.springrest.service;

import com.codewithyassine.springrest.dto.TaskRequest;
import com.codewithyassine.springrest.dto.TaskResponse;
import com.codewithyassine.springrest.dto.UpdateTaskStatusRequest;
import com.codewithyassine.springrest.exception.TaskNotFoundException;
import com.codewithyassine.springrest.model.Task;
import com.codewithyassine.springrest.model.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import com.codewithyassine.springrest.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

        return response;
    }
    public TaskResponse deleteTask(Long id){
        Task task = taskRepository.findById(id).orElse(null);
        taskRepository.delete(task);
        return null;
    }
}
