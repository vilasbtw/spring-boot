package com.kaique.spring.controllers;

import com.kaique.spring.controllers.docs.PersonControllerDocs;
import com.kaique.spring.data.vo.v1.PersonVO;
import com.kaique.spring.services.PersonService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.kaique.spring.util.MediaType;

import java.util.List;

// @CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for managing people")
public class PersonController implements PersonControllerDocs {

    @Autowired
    private PersonService service;

    // @CrossOrigin(origins = {"http://localhost:8080", "https://erudio.com.br"})
    @PostMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Override
    public PersonVO create(@RequestBody PersonVO person) {
        return service.create(person);
    }

    // @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Override
    public PersonVO findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Override
    public List<PersonVO> findAll() {
        return service.findAll();
    }

    @PutMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Override
    public PersonVO update(@RequestBody PersonVO person) {
        return service.update(person);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}