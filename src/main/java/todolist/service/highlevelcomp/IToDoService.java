package todolist.service.highlevelcomp;

import org.springframework.http.ResponseEntity;
import todolist.dto.ToDoDto;
import todolist.entity.ToDo;

import java.util.List;
import java.util.Map;

public interface IToDoService {

    ResponseEntity<List<ToDoDto>> findAll();

    ResponseEntity<ToDoDto> findOne(Integer id);

    ResponseEntity<ToDoDto> update(ToDoDto toDoDto, Integer id);

    ResponseEntity<ToDoDto> create(ToDoDto toDoDto);

    ResponseEntity<Map<String, Object>> delete(Integer id);

    ResponseEntity<Map<String, Object>> deleteAll();

    ResponseEntity<ToDoDto> partialUpdate(Integer id, Map<String, Object> changes);
}
