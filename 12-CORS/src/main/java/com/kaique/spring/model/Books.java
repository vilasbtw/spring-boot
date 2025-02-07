package com.kaique.spring.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "books")
public class Books implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_name", nullable = false, length = 50)
    private String bookName;

    @Column(name = "author_name", nullable = false, length = 50)
    private String authorName;

    @Column(name = "number_of_pages", nullable = false, length = 50)
    private int numberOfPages;

    public Books() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Books books)) return false;
        return numberOfPages == books.numberOfPages && Objects.equals(id, books.id) && Objects.equals(bookName, books.bookName) && Objects.equals(authorName, books.authorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookName, authorName, numberOfPages);
    }
}