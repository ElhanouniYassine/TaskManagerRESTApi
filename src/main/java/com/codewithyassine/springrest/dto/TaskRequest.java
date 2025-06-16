package com.codewithyassine.springrest.dto;

import com.codewithyassine.springrest.model.TaskPriority;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import com.codewithyassine.springrest.model.TaskStatus;

import java.time.LocalDate;
@Getter
@Setter
public class TaskRequest {

    // DTO IS data transfer object used to carry data between layers

    @NotBlank
    private String title;
    @Size(max = 1000)
    private String description;
    @FutureOrPresent
    private LocalDate dueDate;
    @Enumerated(EnumType.STRING)
    private TaskStatus status=TaskStatus.PENDING;
    @Enumerated(EnumType.STRING)
    private TaskPriority priority;
}
