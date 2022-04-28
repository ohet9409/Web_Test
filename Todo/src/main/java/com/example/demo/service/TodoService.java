package com.example.demo.service;

import com.example.demo.TodoRepository;
import com.example.demo.dto.TodoDTO;
import com.example.demo.model.TodoEntity;
import com.sun.tools.javac.comp.Todo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<TodoEntity> retrieve(final String userId) {
        return repository.findByUserId(userId);
    }

    public List<TodoEntity> update(final TodoEntity entity) {
        // (1) ������ ��ƼƼ�� ��ȿ���� Ȯ���Ѵ�. �� �޼���� 2.3.1 Creat Todo���� �����ߴ�.
        validate(entity);

        // (2) �Ѱܹ��� ��ƼƼ id�� �̿��� TodoEntity�� �����´�. �������� �ʴ� ��ƼƼ�� ������Ʈ�� �� ���� �����̴�.
        final Optional<TodoEntity> original = repository.findById(entity.getId());

        log.warn(original.toString());

        if(original.isPresent()) {
            // (3) ��ȯ�� TodoEntity�� �����ϸ� ���� �� entity ������ ���� �����.
            final TodoEntity todo = original.get();
            todo.setTitle(entity.getTitle());
            todo.setDone(entity.isDone());

            // (4) �����ͺ��̽��� �� ���� �����Ѵ�.
            repository.save(todo);
            log.warn("���� ������?");
        }

        // 2.3.2 Retrieve Todo���� ���� �޼��带 �̿��� ������� ��� Todo ����Ʈ�� �����Ѵ�.
        return retrieve(entity.getUserId());
    }

    public List<TodoEntity> delete(final TodoEntity entity) {
        // (1) ������ ��ƼƼ�� ��ȿ���� Ȯ���Ѵ�. �� �޼���� 2.3.1 Create Todo���� �����ߴ�.
        validate(entity);

        try {
            // (2) ��ƼƼ�� �����Ѵ�.
            repository.delete(entity);
        } catch (Exception e) {
            // (3) exception �߻� �� id�� exception�� �α�����.
            log.error("error deleing entity ", entity.getId(), e);

            // (4) ��Ʈ�ѷ��� exception�� ������. �����ͺ��̽� ���� ������ ĸ��ȭ�Ϸ��� e�� �������� �ʰ� �� exception ������Ʈ�� �����Ѵ�.
            throw new RuntimeException("error deleting entity " + entity.getId());
        }

        // (5) �� Todo ����Ʈ�� ������ �����Ѵ�.
        return retrieve(entity.getUserId());
    }
    // �����丵�� �޼���
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
