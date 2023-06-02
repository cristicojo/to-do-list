package todolist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import todolist.entity.ToDo;
import todolist.repository.ToDoRepository;
import todolist.service.ToDoService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ToDoServiceImpl implements ToDoService {

    @Autowired
    private ToDoRepository repository;


    @Override
    public List<ToDo> findAll() {
        return repository.findAll();
    }


    @Override
    public ToDo findOne(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("toDo not found with id: " + id));
    }


    @Override
    public ToDo update(ToDo toDoRequest, Integer id) {
        ToDo newToDo = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("toDo not found with id: " + id));

        newToDo.setTitle(toDoRequest.getTitle());
        newToDo.setDueDate(toDoRequest.getDueDate());
        newToDo.setStatus(toDoRequest.getStatus());

        return repository.save(newToDo);
    }


    @Override
    public ToDo create(ToDo toDoRequest) {
        return repository.save(toDoRequest);
    }


    @Override
    public void delete(Integer id) {
        repository.delete(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("toDo not found with id: " + id)));
    }


    @Override
    public void deleteAll() {
        repository.deleteAll();
    }


    @Override
    public ToDo partialUpdate(Integer id, String request) {
        ToDo newToDo = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("toDo not found with id: " + id));

        switch (request) {
            case "title" -> newToDo.setTitle(request);
            case "dueDate" -> newToDo.setDueDate(LocalDateTime.parse(request));
            case "status" -> newToDo.setStatus(request);
        }

        return repository.save(newToDo);
    }

}
