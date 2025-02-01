package com.kaique.spring.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaique.spring.controllers.PersonController;
import com.kaique.spring.data.vo.v1.PersonVO;
import com.kaique.spring.exceptions.RequiredObjectIsNullException;
import com.kaique.spring.exceptions.ResourceNotFoundException;
import com.kaique.spring.mapper.DozerMapper;
import com.kaique.spring.model.Person;
import com.kaique.spring.repositories.PersonRepository;

@Service
public class PersonService {

    // Simulates ID
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    private PersonRepository repository;
    // private PersonRepository repository = new PersonRepository();

    public PersonVO create(PersonVO personVO) {
    	if (personVO == null) throw new RequiredObjectIsNullException();
    	
        logger.info("Creating a person");

        Person entity = DozerMapper.parseObjects(personVO, Person.class);
        PersonVO vo = DozerMapper.parseObjects(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public PersonVO findById(Long id) {
        logger.info("Finding a person!");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found."));

        PersonVO vo = DozerMapper.parseObjects(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findAll()).withRel("List of all people"));
        return vo;
    }

    public List<PersonVO> findAll() {
        logger.info("Finding all persons!");

        List<PersonVO> vos = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);

        for (PersonVO v : vos) {
            Long id = v.getKey();
            v.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        }
        // vos.forEach(v -> v.add(linkTo(methodOn(PersonController.class).findById(v.getKey())).withSelfRel()));
        return vos;
    }

    public PersonVO update(PersonVO personVO) {
    	if (personVO == null) throw new RequiredObjectIsNullException();
    	
        logger.info("Updating a person");

        Person entity = repository.findById(personVO.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found."));

        entity.setFirstName(personVO.getFirstName());
        entity.setLastName(personVO.getLastName());
        entity.setAddress(personVO.getAddress());
        entity.setGender(personVO.getGender());

        PersonVO vo = DozerMapper.parseObjects(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id) {
        logger.info("Deleting a person");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found."));

        repository.delete(entity);
    }
}