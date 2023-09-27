package todolist.service.highlevelcomp;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import todolist.dto.ToDoDto;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ToDoService {

    private final IToDoService iToDoService;


    public ResponseEntity<List<ToDoDto>> findAll() {
        return iToDoService.findAll();
    }

    public ResponseEntity<ToDoDto> findOne(Integer id) {
        return iToDoService.findOne(id);
    }

    public ResponseEntity<ToDoDto> update(ToDoDto toDoDto, Integer id) {
        return iToDoService.update(toDoDto, id);
    }

    public ResponseEntity<ToDoDto> create(ToDoDto toDoDto) {
        return iToDoService.create(toDoDto);
    }

    public ResponseEntity<Map<String, Object>> delete(Integer id) {
        return iToDoService.delete(id);
    }

    public ResponseEntity<Map<String, Object>> deleteAll() {
        return iToDoService.deleteAll();
    }

    public ResponseEntity<ToDoDto> partialUpdate(Integer id, Map<String, Object> changes) {
        return iToDoService.partialUpdate(id, changes);
    }
}
