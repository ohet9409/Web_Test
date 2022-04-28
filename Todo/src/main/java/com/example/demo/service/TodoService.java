package com.example.demo.service;

import com.example.demo.TodoRepository;
import com.example.demo.dto.TodoDTO;
import com.example.demo.model.TodoEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public List<TodoEntity> create(final TodoEntity entity){
        // Validations
        validate(entity);

        repository.save(entity);

        log.info("Entity Id : {} is saved", entity.getId());

        return repository.findByUserId(entity.getUserId());
    }

    // 리펙토링한 메서드
    private void validate(final TodoEntity entity) {
        if(entity == null){
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null.");
        }

        if(entity.getUserId() == null){
            log.warn("unknown user.");
            throw new RuntimeException("Unknown user.");
        }
    }
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
