package com.kaique.spring.controllers;

import com.kaique.spring.Hello;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private String template = "Hello, %s!";

    @RequestMapping("/hello")
    public Hello sayHello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Hello(String.format(template, name));
    }
}
