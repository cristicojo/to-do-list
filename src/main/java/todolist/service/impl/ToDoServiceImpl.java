package todolist.service.impl;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class ToDoServiceImpl implements ToDoService {

    private final ToDoRepository repository;
    private final ModelMapper modelMapper;


    @Override
    public ResponseEntity<List<ToDoDto>> findAll() {
        var list = repository.findAll().stream()
                .map(toDo -> modelMapper.map(toDo, ToDoDto.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ToDoDto> findOne(Integer id) {
        var toDoDto = modelMapper.map(repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("ToDo not found with id: " + id)), ToDoDto.class);

        return new ResponseEntity<>(toDoDto, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ToDoDto> update(ToDoDto toDoDto, Integer id) {
        ToDo newToDo = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ToDo not found with id: " + id));
        var toDoRequest = modelMapper.map(toDoDto, ToDo.class);

        newToDo.setTitle(toDoRequest.getTitle());
        newToDo.setDueDate(toDoRequest.getDueDate());
        newToDo.setStatus(toDoRequest.getStatus());

        return new ResponseEntity<>(modelMapper.map(repository.save(newToDo), ToDoDto.class), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ToDoDto> create(ToDoDto toDoDto) {
        return new ResponseEntity<>(modelMapper.map(repository.save(modelMapper.map(toDoDto, ToDo.class)),
                ToDoDto.class), HttpStatus.CREATED);
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
    public ResponseEntity<Map<String, Object>> deleteAll() {
        repository.deleteAll();
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", "All ToDos deleted successfully");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ToDoDto> partialUpdate(Integer id, Map<String, Object> changes) {
        ToDo newToDo = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ToDo not found with id: " + id));

        changes.forEach((change, value) -> {
            switch (change) {
                case "title" -> newToDo.setTitle((String) value);
                case "dueDate" -> newToDo.setDueDate((LocalDate) value);
                case "status" -> newToDo.setStatus((String) value);
                case "description" -> newToDo.setDescription((String) value);
            }
        });

        return new ResponseEntity<>(modelMapper.map(repository.save(newToDo), ToDoDto.class), HttpStatus.OK);
    }
}
