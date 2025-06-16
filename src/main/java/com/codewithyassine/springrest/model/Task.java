package com.codewithyassine.springrest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
