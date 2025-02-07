package com.kaique.spring.repositories;

import com.kaique.spring.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {}
// JpaRepository offers all simple CRUD methods, making development easier.