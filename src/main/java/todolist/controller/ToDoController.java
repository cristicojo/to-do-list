package todolist.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todolist.dto.ToDoDto;
import todolist.entity.ToDo;
import todolist.service.ToDoService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ToDoController {

    private ToDoService service;


    @GetMapping("/all")
    public List<ToDoDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/todo/{id}")
    public ToDoDto getById(@PathVariable("id") Integer id) {
        return service.findOne(id);
    }

    @PostMapping("/todo")
    public ToDoDto save(@Valid @RequestBody ToDo toDo) {
        return service.create(toDo);
    }

    @PutMapping("/todo/{id}")
    public ToDoDto update(@PathVariable("id") Integer id, @Valid @RequestBody ToDo toDo) {
        return service.update(toDo, id);
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<Map<String, Object>> deleteById(@PathVariable("id") Integer id) {
        return service.delete(id);

    }

    @DeleteMapping("/all")
    public void deleteAll() {
        service.deleteAll();
    }


    @PatchMapping("/todo/{id}")
    public ToDoDto updateStatus(@PathVariable Integer id, @RequestBody Map<String, Object> changes) {
        return service.partialUpdate(id, changes);
    }

}
