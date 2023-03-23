package com.example.first_project.api;

import org.springframework.web.bind.annotation.*;

@RestController
public class apiController {

    @GetMapping("api/HelloWorld")
    public String HelloWorldGet(){
        return "Hello WorldGet";

    }

    @PostMapping("api/HelloWorld")
    public String HelloWorldPost(){

        return "HelloWorldPost";
    }

    @PutMapping("api/HelloWorld")
    public String HelloWorldPut(){
        return "HelloWorldPut";
    }

    @DeleteMapping("api/HelloWorld")
    public String HelloWorldDelete(){
        return "HelloWorldDelete";
    }


}
