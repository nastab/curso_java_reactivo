package com.curso.reactividad.introtospring.repository;

import com.curso.reactividad.introtospring.repository.model.ToDo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ToDoRepository {

    private List<ToDo> list;

    public ToDoRepository(){
        list = new ArrayList<>();
    }

    public ToDo addTask(String task){
        ToDo newTask = new ToDo(task);
        list.add(newTask);
        return newTask;
    }

    public List<ToDo> getTasks(){
        return list;
    }

    public ToDo updateTask(int id, String newTask){
        ToDo task = list.stream()
                .filter(toDo -> toDo.id == id)
                .findFirst()
                .get();
        task.task = newTask;
        return task;
    }

    public String deleteTask(int id){
        if (list.stream().noneMatch(toDo -> toDo.id == id))
            return "ToDo not found";
        list.removeIf(toDo -> toDo.id == id);
        return "ToDo deleted";
    }
}
