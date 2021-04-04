package com.myproject.myprojec.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "book_author")
public class BookAuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity books;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorEntity authors;

    public BookAuthorEntity() {
    }

    public BookAuthorEntity(Long id, BookEntity books, AuthorEntity authors) {
        this.id = id;
        this.books = books;
        this.authors = authors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookEntity getBooks() {
        return books;
    }

    public void setBooks(BookEntity books) {
        this.books = books;
    }

    public AuthorEntity getAuthors() {
        return authors;
    }

    public void setAuthors(AuthorEntity authors) {
        this.authors = authors;
    }


}
