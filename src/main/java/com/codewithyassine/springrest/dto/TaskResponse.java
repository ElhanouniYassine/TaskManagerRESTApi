package com.codewithyassine.springrest.dto;

import com.codewithyassine.springrest.model.TaskPriority;
import lombok.Getter;
import lombok.Setter;
import com.codewithyassine.springrest.model.TaskStatus;

import java.time.LocalDate;
@Getter
@Setter
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private TaskStatus status=TaskStatus.PENDING;
    private TaskPriority priority;
}
