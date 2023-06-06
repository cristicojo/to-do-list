package todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todolist.entity.ToDo;

public interface ToDoRepository extends JpaRepository<ToDo, Integer> {
}
