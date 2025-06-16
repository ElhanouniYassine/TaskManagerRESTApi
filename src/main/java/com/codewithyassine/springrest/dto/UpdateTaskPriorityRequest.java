package com.codewithyassine.springrest.dto;

import com.codewithyassine.springrest.model.TaskPriority;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTaskPriorityRequest {
    @NotNull
    private TaskPriority priority;

}
