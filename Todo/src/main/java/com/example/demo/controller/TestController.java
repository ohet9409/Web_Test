package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TestRequestBodyDTO;
import com.example.demo.model.TodoEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("/testResponseEntity")
    public ResponseEntity<?> testControllerResponseEntity(){
        List<String> list = new ArrayList<>();
        list.add("Hello World! I'm ResponseEntity. And you got 400!");
        ResponseDTO<String> responseDTO = ResponseDTO.<String>builder().data(list).build();
        // http status�� 400���� ����
        //return ResponseEntity.badRequest().body(responseDTO);

        // http status�� 200���� ����
        return ResponseEntity.ok().body(responseDTO);
    }
    @GetMapping("/testResponseBody")
    public ResponseDTO<String> testControllerResponseBody() {
        List<String> list = new ArrayList<>();
        list.add("Hello World! I'm ResponseDTO");
        ResponseDTO<String> responseDTO = ResponseDTO.<String>builder().data(list).build();
        return  responseDTO;
    }

    @GetMapping("/testRequestBody")
    public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO){
        return "Hello World! ID " + testRequestBodyDTO.getId() + " Message : " + testRequestBodyDTO.getMessage();
    }

    @GetMapping
    public String testContoller(){
        return "Hello World";
    }

   // @GetMapping("/testGetMapping")
    @GetMapping("/test/testGetMapping")
    public String testControllerWithPath(){
        return "Hello World! testGetMapping";
    }

    @GetMapping("/{id}")
    public String testControllerWithPathVariables(@PathVariable(required = false) int id) {
        return "Hello World! ID " + id;
    }

    @GetMapping("/testRequeltParam")
    public String testControllerRequestParam(@RequestParam(required = false) int id){
        TodoEntity todoEntity = TodoEntity.builder()
                .id("id-11")
                .userId("developer")
                .title("test")
                .build();
        return "Hello World! ID " + id +" " + todoEntity.toString();
    }
}
