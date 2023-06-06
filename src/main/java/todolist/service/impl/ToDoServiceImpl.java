package todolist.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import todolist.dto.ToDoDto;
import todolist.entity.ToDo;
import todolist.repository.ToDoRepository;
import todolist.service.ToDoService;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ToDoServiceImpl implements ToDoService {

    private ToDoRepository repository;
    private ModelMapper modelMapper;


    @Override
    public List<ToDoDto> findAll() {
        return repository.findAll().stream()
                .map(toDo -> modelMapper.map(toDo, ToDoDto.class))
                .collect(Collectors.toList());
    }


    @Override
    public ToDoDto findOne(Integer id) {
        return modelMapper.map(repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("ToDo not found with id: " + id)), ToDoDto.class);
    }


    @Override
    public ToDoDto update(ToDo toDoRequest, Integer id) {
        ToDo newToDo = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ToDo not found with id: " + id));

        newToDo.setTitle(toDoRequest.getTitle());
        newToDo.setDueDate(toDoRequest.getDueDate());
        newToDo.setStatus(toDoRequest.getStatus());

        return modelMapper.map(repository.save(newToDo), ToDoDto.class);
    }


    @Override
    public ToDoDto create(ToDo toDoRequest) {
        return modelMapper.map(repository.save(toDoRequest), ToDoDto.class);
    }


    @Override
    public ResponseEntity<Map<String, Object>> delete(Integer id) {
        repository.delete(repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("ToDo not found with id: " + id)));

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", "ToDo with id: " + id + " deleted successfully");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }


    @Override
    public void deleteAll() {
        repository.deleteAll();
    }


    @Override
    public ToDoDto partialUpdate(Integer id, Map<String, Object> changes) {
        ToDo newToDo = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ToDo not found with id: " + id));

        changes.forEach((change, value) -> {
            switch (change) {
                case "title" -> newToDo.setTitle((String) value);
                case "dueDate" -> newToDo.setDueDate((LocalDate) value);
                case "status" -> newToDo.setStatus((String) value);
                case "description" -> newToDo.setDescription((String) value);
            }
        });

        return modelMapper.map(repository.save(newToDo), ToDoDto.class);
    }
}
