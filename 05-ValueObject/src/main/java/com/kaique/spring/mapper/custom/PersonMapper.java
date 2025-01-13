package com.kaique.spring.mapper.custom;

import com.kaique.spring.data.vo.v2.PersonVOV2;
import com.kaique.spring.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonVOV2 convertEntityToVO(Person person) {
        PersonVOV2 vo = new PersonVOV2();

        vo.setId(person.getId());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setAddress(person.getAddress());
        vo.setGender(person.getGender());
        vo.setBirthday(new Date());

        return vo;
    }

    public Person convertVOToEntity(PersonVOV2 vo) {
        Person entity = new Person();

        entity.setId(vo.getId());
        entity.setFirstName(vo.getFirstName());
        entity.setLastName(vo.getLastName());
        entity.setAddress(vo.getAddress());
        entity.setGender(vo.getGender());

        return entity;
    }
}
