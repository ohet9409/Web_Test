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
        // TodoEntity 생성
        TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
        // TodoEntity 저장
        repository.save(entity);
        System.out.println(entity.toString());


        // TodoEntity 검색
        TodoEntity savedEntity = repository.findById(entity.getId()).get();

        //return "Test Service";
        return savedEntity.getTitle() + ", " + savedEntity.getId() + ", " + savedEntity.getUserId();
//        return savedEntity.getTitle();
    }
}
