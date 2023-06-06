package todolist.service;

import org.springframework.http.ResponseEntity;
import todolist.dto.ToDoDto;
import todolist.entity.ToDo;

import java.util.List;
import java.util.Map;

public interface ToDoService {

    List<ToDoDto> findAll();

    ToDoDto findOne(Integer id);

    ToDoDto update(ToDo toDoRequest, Integer id);

    ToDoDto create(ToDo toDoRequest);

    ResponseEntity<Map<String, Object>> delete(Integer id);

    void deleteAll();

    ToDoDto partialUpdate(Integer id, Map<String, Object> changes);
}
