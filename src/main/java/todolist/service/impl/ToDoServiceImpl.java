package todolist.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import todolist.entity.ToDo;
import todolist.repository.ToDoRepository;
import todolist.service.ToDoService;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ToDoServiceImpl implements ToDoService {

    private ToDoRepository repository;


    @Override
    public List<ToDo> findAll() {
        return repository.findAll();
    }


    @Override
    public ToDo findOne(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ToDo not found with id: " + id));
    }


    @Override
    public ToDo update(ToDo toDoRequest, Integer id) {
        ToDo newToDo = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ToDo not found with id: " + id));

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
        repository.delete(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ToDo not found with id: " + id)));
    }


    @Override
    public void deleteAll() {
        repository.deleteAll();
    }


    @Override
    public ToDo partialUpdate(Integer id, String requestParam, HttpServletRequest httpServletRequest) {
        ToDo newToDo = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ToDo not found with id: " + id));

        var parameterName = httpServletRequest.getParameterNames().nextElement();
        switch (parameterName) {
            case "title" -> newToDo.setTitle(requestParam);
            case "dueDate" -> newToDo.setDueDate(LocalDate.parse(requestParam));
            case "status" -> newToDo.setStatus(requestParam);
            case "description" -> newToDo.setDescription(requestParam);
        }

        return repository.save(newToDo);
    }
}
