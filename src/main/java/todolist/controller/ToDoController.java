package todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import todolist.entity.ToDo;
import todolist.service.impl.ToDoServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(name="/api")
public class ToDoController {

    @Autowired
    private ToDoServiceImpl service;


    @GetMapping("/all")
    public List<ToDo> getAll(){
        return service.findAll();
    }

    @GetMapping("/todo/{id}")
    public ToDo getToDoById(@PathVariable(name="id") Integer id) {
        return service.findOne(id);
    }

    @PostMapping("/todo")
    public ToDo saveToDo(@RequestBody ToDo p) {
        return service.create(p);
    }

    @PutMapping("/todo/{id}")
    public ToDo updateToDo(@PathVariable(name="id") Integer id, @RequestBody ToDo p) {
        return service.update(p, id);
    }

    @DeleteMapping("/todo/{id}")
    public void deleteToDoById(@PathVariable(name="id") Integer id) {
        service.delete(id);

    }

    @DeleteMapping("/all")
    public void deleteAllToDo() {
        service.deleteAll();
    }


    @PatchMapping("/todo/{id}/{title}")
    public ToDo updateTitle(@PathVariable Integer id, @PathVariable String title) {
        return service.partialUpdate(id, title);
    }

    @PatchMapping("/todo/{id}/{dueDate}")
    public ToDo updateDueDate(@PathVariable Integer id, @PathVariable LocalDateTime dueDate) {
        return service.partialUpdate(id, String.valueOf(dueDate));
    }

    @PatchMapping("/todo/{id}/{status}")
    public ToDo updateStatus(@PathVariable Integer id, @PathVariable String status) {
        return service.partialUpdate(id, status);
    }
}
