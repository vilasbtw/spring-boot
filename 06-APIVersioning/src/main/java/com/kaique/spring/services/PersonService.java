package com.kaique.spring.services;

import com.kaique.spring.data.vo.v1.PersonVO;
import com.kaique.spring.data.vo.v2.PersonVOV2;
import com.kaique.spring.exceptions.ResourceNotFoundException;
import com.kaique.spring.mapper.DozerMapper;
import com.kaique.spring.mapper.custom.PersonMapper;
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

    @Autowired
    private PersonMapper mapper;

    public PersonVO create(PersonVO personVO) {
        logger.info("Creating a person");

        Person entity = DozerMapper.parseObjects(personVO, Person.class);
        return DozerMapper.parseObjects(repository.save(entity), PersonVO.class);
    }

    public PersonVOV2 create(PersonVOV2 personVO) {
        logger.info("[V2] Creating a person");

        Person entity = mapper.convertVOToEntity(personVO);
        return mapper.convertEntityToVO(repository.save(entity));
    }

    public PersonVO findById(Long id) {
        logger.info("Finding a person!");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found."));

        return DozerMapper.parseObjects(entity, PersonVO.class);
    }

    public List<PersonVO> findAll() {
        logger.info("Finding all persons!");

        return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO update(PersonVO person) {
        logger.info("Updating a person");

        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found."));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return DozerMapper.parseObjects(repository.save(entity), PersonVO.class);
    }

    public void delete(Long id) {
        logger.info("Deleting a person");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found."));

        repository.delete(entity);
    }
}