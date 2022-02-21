package ru.innop.tasktracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.innop.tasktracker.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
