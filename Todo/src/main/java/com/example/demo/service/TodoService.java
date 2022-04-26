package com.example.demo.service;

import com.example.demo.TodoRepository;
import com.example.demo.model.TodoEntity;
import com.sun.tools.javac.comp.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public String testService() {
        // TodoEntity ����
        TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
        // TodoEntity ����
        repository.save(entity);
        System.out.println(entity.toString());


        // TodoEntity �˻�
        TodoEntity savedEntity = repository.findById(entity.getId()).get();

        //return "Test Service";
        return savedEntity.getTitle() + ", " + savedEntity.getId() + ", " + savedEntity.getUserId();
//        return savedEntity.getTitle();
    }
}
