package com.kaique.spring.services;

import com.kaique.spring.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    // Simulates ID
    private AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public Person create(Person person) {
        logger.info("Creating a person");
        return person;
    }

    public Person findById(String id) {
        logger.info("Finding one person!");

        Person person = new Person();

        person.setId(counter.getAndIncrement());
        person.setFirstName("Kaique");
        person.setLastName("Vilas");
        person.setAdress("BR");
        person.setGender("Male");
        return person;
    }

    public List<Person> findAll() {
        logger.info("Finding all persons!");

        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }

    public Person update(Person person) {
        logger.info("Updating a person");
        return person;
    }

    public void delete(String id) {
        logger.info("Deleting a person");
    }

    public Person mockPerson(int id) {
        Person person = new Person();

        person.setId(counter.getAndIncrement());
        person.setFirstName("First name: " + id);
        person.setLastName("Last name: " + id);
        person.setAdress("Adress: " + id);
        person.setGender("Gender: " + id);
        return person;
    }
}