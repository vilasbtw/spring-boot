package com.kaique.spring.unittests.mapper.mocks;

import java.util.ArrayList;
import java.util.List;

import com.kaique.spring.data.vo.v1.BooksVO;
import com.kaique.spring.model.Books;

public class MockBooks {

    public Books mockEntity() {
        return mockEntity(0);
    }

    public BooksVO mockVO() {
        return mockVO(0);
    }

    public List<Books> mockEntityList() {
        List<Books> books = new ArrayList<Books>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BooksVO> mockVOList() {
        List<BooksVO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockVO(i));
        }
        return books;
    }

    public Books mockEntity(Integer number) {
        Books book = new Books();
        book.setBookName("Book Name Test" + number);
        book.setAuthorName("Author Name Test" + number);
        book.setNumberOfPages(number);
        book.setId(number.longValue());
        return book;
    }

    public BooksVO mockVO(Integer number) {
        BooksVO books = new BooksVO();
        books.setBookName("Book Name Test" + number);
        books.setAuthorName("Author Name Test" + number);
        books.setNumberOfPages(number);
        books.setKey(number.longValue());
        return books;
    }
}