package todolist.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import todolist.entity.ToDo;
import todolist.service.ToDoService;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ToDoController {

    private ToDoService service;


    @GetMapping("/all")
    public List<ToDo> getAll(){
        return service.findAll();
    }

    @GetMapping("/todo/{id}")
    public ToDo getById(@PathVariable("id") Integer id) {
        return service.findOne(id);
    }

    @PostMapping("/todo")
    public ToDo save(@Valid @RequestBody ToDo toDo) {
        return service.create(toDo);
    }

    @PutMapping("/todo/{id}")
    public ToDo update(@PathVariable("id") Integer id, @RequestBody ToDo toDo) {
        return service.update(toDo, id);
    }

    @DeleteMapping("/todo/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        service.delete(id);

    }

    @DeleteMapping("/all")
    public void deleteAll() {
        service.deleteAll();
    }


    @PatchMapping("/status/{id}")
    public ToDo updateStatus(@PathVariable Integer id, @RequestParam("status") String requestParam, HttpServletRequest request) {
        return service.partialUpdate(id, requestParam, request);
    }

    @PatchMapping("/description/{id}")
    public ToDo updateDescription(@PathVariable Integer id, @RequestParam("description") String requestParam, HttpServletRequest request) {
        return service.partialUpdate(id, requestParam, request);
    }

    @PatchMapping("/dueDate/{id}")
    public ToDo updateDueDate(@PathVariable Integer id, @RequestParam("dueDate") String requestParam, HttpServletRequest request) {
        return service.partialUpdate(id, requestParam, request);
    }

    @PatchMapping("/title/{id}")
    public ToDo updateTitle(@PathVariable Integer id, @RequestParam("title") String requestParam, HttpServletRequest request) {
        return service.partialUpdate(id, requestParam, request);
    }
}
