package todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import todolist.entity.ToDo;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Integer> {
}
