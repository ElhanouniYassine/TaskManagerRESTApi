package com.codewithyassine.springrest.repository;

import com.codewithyassine.springrest.model.Task;
import com.codewithyassine.springrest.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {



    // Optional: Custom finder
    List<Task> findByStatus(TaskStatus status);



}
