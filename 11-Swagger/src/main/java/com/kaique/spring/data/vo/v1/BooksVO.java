package com.kaique.spring.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@JsonPropertyOrder({"id", "bookName", "authorName", "numberOfPages"})
public class BooksVO extends RepresentationModel<BooksVO> implements Serializable {

    private static final Long serialVersionUID = 1L;

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

    public void setId(Long id) {
        this.key = id;
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
}