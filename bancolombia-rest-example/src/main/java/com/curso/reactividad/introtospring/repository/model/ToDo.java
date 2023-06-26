package com.curso.reactividad.introtospring.repository.model;

public class ToDo {
    public static int counter = 0;

    public int id;
    public String task;

    public ToDo(String task){
        counter++;
        this.id = counter;
        this.task = task;
    }
}
