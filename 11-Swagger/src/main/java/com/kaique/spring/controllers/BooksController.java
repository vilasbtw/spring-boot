package com.kaique.spring.controllers;

import com.kaique.spring.model.Books;
import com.kaique.spring.services.BooksService;
import com.kaique.spring.util.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/* TO-DO:
    1. Implement Value Object Patter;
    2. Implement content negotiation;
    3. Implement support to HATEOAS;
    4. Implement support to Swagger OpenAPI;
*/

@RestController
@RequestMapping("/api/books/v1")
public class BooksController {

    @Autowired
    BooksService service;

    @PostMapping(produces = {MediaType.APPLICATION_JSON}, consumes = {MediaType.APPLICATION_JSON})
    public Books create(@RequestBody Books book) {
        return service.create(book);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON})
    public Books findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON})
    public List<Books> findAll() {
        return service.findAll();
    }

    @PutMapping(produces =  {MediaType.APPLICATION_JSON}, consumes = {MediaType.APPLICATION_JSON})
    public Books update(@RequestBody Books book) {
        return service.update(book);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }
}