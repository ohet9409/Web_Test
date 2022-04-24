package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
public class TestController { // ¸®¼Ò½º

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
        return "Hello World! ID " + id;
    }
}
