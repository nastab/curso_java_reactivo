package com.curso.reactividad.introtospring.service;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
public class ToDoService {

    private String helloWorld = "Hello Bancolombia's team";

    public String saludar(){
        return helloWorld;
    }
}
