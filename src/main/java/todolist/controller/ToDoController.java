package todolist.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todolist.dto.ToDoDto;
import todolist.entity.ToDo;
import todolist.service.ToDoService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoService service;


    @GetMapping("/all")
    public ResponseEntity<List<ToDoDto>> getAll() {
        return service.findAll();
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<ToDoDto> getById(@PathVariable("id") Integer id) {
        return service.findOne(id);
    }

    @PostMapping("/todo")
    public ResponseEntity<ToDoDto> save(@Valid @RequestBody ToDoDto toDoDto) {
        return service.create(toDoDto);
    }

    @PutMapping("/todo/{id}")
    public ResponseEntity<ToDoDto> update(@PathVariable("id") Integer id, @Valid @RequestBody ToDoDto toDoDto) {
        return service.update(toDoDto, id);
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<Map<String, Object>> deleteById(@PathVariable("id") Integer id) {
        return service.delete(id);

    }

    @DeleteMapping("/all")
    public ResponseEntity<Map<String, Object>> deleteAll() {
        return service.deleteAll();
    }


    @PatchMapping("/todo/{id}")
    public ResponseEntity<ToDoDto> partialUpdate(@PathVariable Integer id, @RequestBody Map<String, Object> changes) {
        return service.partialUpdate(id, changes);
    }

}
