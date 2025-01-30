package com.kaique.spring.services;

import com.kaique.spring.data.vo.v1.BooksVO;
import com.kaique.spring.exceptions.RequiredObjectIsNullException;
import com.kaique.spring.exceptions.ResourceNotFoundException;
import com.kaique.spring.mapper.DozerMapper;
import com.kaique.spring.model.Books;
import com.kaique.spring.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksService {

    @Autowired
    BooksRepository repository;

    public BooksVO create(BooksVO bookVO) {
        if (bookVO == null) throw new RequiredObjectIsNullException();

        Books entity = DozerMapper.parseObjects(bookVO, Books.class);
        BooksVO vo = DozerMapper.parseObjects(repository.save(entity), BooksVO.class);

        return vo;
    }

    public BooksVO findById(Long id) {
        Books entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found."));

        BooksVO vo = DozerMapper.parseObjects(entity, BooksVO.class);
        return vo;
    }

    public List<BooksVO> findAll() {
        List<BooksVO> vos = DozerMapper.parseListObjects(repository.findAll(), BooksVO.class);
        return vos;
    }

    public BooksVO update(BooksVO bookVO) {
        if (bookVO == null) throw new RequiredObjectIsNullException();

        Books entity = repository.findById(bookVO.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found."));

        entity.setBookName(bookVO.getBookName());
        entity.setAuthorName(bookVO.getAuthorName());
        entity.setNumberOfPages(bookVO.getNumberOfPages());

        BooksVO vo = DozerMapper.parseObjects(repository.save(entity), BooksVO.class);
        return vo;
    }

    public void delete(Long id) {

        Books entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found."));

        repository.delete(entity);
    }
}