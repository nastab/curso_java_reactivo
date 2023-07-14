package com.santiago.posada.controller;

import com.santiago.posada.repository.model.ToDo;
import com.santiago.posada.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@RestController
public class ToDoController {

    /**
     * 1 - Server response
     * 2 - Router functions
     * 3 - Handle business errors
     * */

    @Autowired
    private ToDoService service;


    @Bean
    public RouterFunction<ServerResponse> getAll(){
        return route(GET("toDo/get/all"),
            request -> ServerResponse.ok().body(
                    BodyInserters.fromPublisher(service.getTasks(), ToDo.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> createTask(){
        return route(POST("toDo/create/task/{task}"),
                request -> ServerResponse.ok().body(
                        BodyInserters.fromPublisher(service.createTask(request.pathVariable("task")), ToDo.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> updateTask(){
        return route(PUT("toDo/update/task/{id}/{newTask}"),
                request -> ServerResponse.ok().body(
                        BodyInserters.fromPublisher(service.updateTask(request.pathVariable("id"),
                                request.pathVariable("newTask")), ToDo.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> deleteTask(){
        return route(DELETE("toDo/delete/task/{id}"),
                request -> ServerResponse.ok().body(
                        BodyInserters.fromPublisher(service.deleteTask(request.pathVariable("id")), ToDo.class)));
    }
}
