package com.kaique.spring.controllers;

import com.kaique.spring.data.vo.v1.BooksVO;
import com.kaique.spring.services.BooksService;
import com.kaique.spring.util.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/* TO-DO:
    1. Implement content negotiation;
    2. Implement support to HATEOAS;
    3. Implement support to Swagger OpenAPI;
*/

@RestController
@RequestMapping("/api/books/v1")
public class BooksController {

    @Autowired
    BooksService service;

    @PostMapping(produces = {MediaType.APPLICATION_JSON}, consumes = {MediaType.APPLICATION_JSON})
    public BooksVO create(@RequestBody BooksVO bookVO) {
        return service.create(bookVO);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON})
    public BooksVO findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON})
    public List<BooksVO> findAll() {
        return service.findAll();
    }

    @PutMapping(produces = {MediaType.APPLICATION_JSON}, consumes = {MediaType.APPLICATION_JSON})
    public BooksVO update(@RequestBody BooksVO bookVO) {
        return service.update(bookVO);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }
}