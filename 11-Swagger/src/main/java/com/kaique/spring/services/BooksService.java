package com.kaique.spring.services;

import com.kaique.spring.exceptions.RequiredObjectIsNullException;
import com.kaique.spring.exceptions.ResourceNotFoundException;
import com.kaique.spring.model.Books;
import com.kaique.spring.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksService {

    @Autowired
    BooksRepository repository;

    public Books create(Books book) {
        if (book == null) throw new RequiredObjectIsNullException();
        return repository.save(book);
    }

    public Books findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found."));
    }

    public List<Books> findAll() {
        return repository.findAll();
    }

    public Books update(Books book) {

        if (book == null) throw new RequiredObjectIsNullException();

        Books entity = repository.findById(book.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found."));

        entity.setBookName(book.getBookName());
        entity.setAuthorName(book.getAuthorName());
        entity.setNumberOfPages(book.getNumberOfPages());

        return repository.save(book);
    }

    public void delete(Long id) {

        Books entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found."));

        repository.delete(entity);
    }
}