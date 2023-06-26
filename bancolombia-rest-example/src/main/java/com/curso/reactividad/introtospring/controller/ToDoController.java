package com.curso.reactividad.introtospring.controller;


import com.curso.reactividad.introtospring.repository.ToDoRepository;
import com.curso.reactividad.introtospring.repository.model.ToDo;
import com.curso.reactividad.introtospring.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/toDo")
public class ToDoController {

    @Autowired
    private ToDoService service;

    @Autowired
    private ToDoRepository repo;

    @GetMapping("/helloWorld")
    public String helloWorld(){
        return service.saludar();
    }

    @PostMapping("/create/task/{task}")
    public ToDo createToDo(@PathVariable("task") String task){
        return repo.addTask(task);
    }

    @GetMapping("/get/all")
    public List<ToDo> getAllTasks(){
        return repo.getTasks();
    }

    //Generar la petición para actualizar una tarea ya existente
    @PutMapping("/update/task/{id}")
    public ToDo updateToDo(@PathVariable("id") Integer id, @RequestParam("newtask") String newTask){
        return repo.updateTask(id, newTask);
    }

    //Generar el método que permite eliminar una tarea
    @DeleteMapping("/delete/task/{id}")
    public String deleteTask(@PathVariable("id") Integer id){
        return repo.deleteTask(id);
    }
}
