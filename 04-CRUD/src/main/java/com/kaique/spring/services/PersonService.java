package com.kaique.spring.services;

import com.kaique.spring.exceptions.ResourceNotFoundException;
import com.kaique.spring.model.Person;
import com.kaique.spring.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    // Simulates ID
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    private PersonRepository repository;
    // private PersonRepository repository = new PersonRepository();

    public Person create(Person person) {
        logger.info("Creating a person");
        return repository.save(person);
    }

    public Person findById(Long id) {
        logger.info("Finding a person!");

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found."));
    }

    public List<Person> findAll() {
        logger.info("Finding all persons!");

        return repository.findAll();
    }

    public Person update(Person person) {
        logger.info("Updating a person");

        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found."));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAdress(person.getAdress());
        entity.setGender(person.getGender());

        return repository.save(person);
    }

    public void delete(Long id) {
        logger.info("Deleting a person");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found."));

        repository.delete(entity);
    }
}