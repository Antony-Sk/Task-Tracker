package ru.innop.tasktracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.innop.tasktracker.models.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByProjectId(Long projectId);

    void deleteAllByProjectId(Long projectId);
}
