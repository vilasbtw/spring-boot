package com.kaique.spring.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Objects;

@JsonPropertyOrder({"id", "bookName", "authorName", "numberOfPages"})
public class BooksVO extends RepresentationModel<BooksVO> implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Mapping("id")
    @JsonProperty("id")
    private Long key;
    private String bookName;
    private String authorName;
    private int numberOfPages;

    public BooksVO() {
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
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
        if (!(o instanceof BooksVO booksVO)) return false;
        if (!super.equals(o)) return false;
        return numberOfPages == booksVO.numberOfPages && Objects.equals(key, booksVO.key) && Objects.equals(bookName, booksVO.bookName) && Objects.equals(authorName, booksVO.authorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), key, bookName, authorName, numberOfPages);
    }
}