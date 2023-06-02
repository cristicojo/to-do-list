package todolist.service;

import todolist.entity.ToDo;
import java.util.List;

public interface ToDoService {

    List<ToDo> findAll();

    ToDo findOne(Integer id);

    ToDo update(ToDo toDoRequest, Integer id);

    ToDo create(ToDo toDoRequest);

    void delete(Integer id);

    void deleteAll();

    ToDo partialUpdate(Integer id, String request);
}
