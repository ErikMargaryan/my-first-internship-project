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
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private BookEntity book;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private AuthorEntity author;

    public BookAuthorEntity() {
    }

    public BookAuthorEntity(Long id, BookEntity book, AuthorEntity author) {
        this.id = id;
        this.book = book;
        this.author = author;
    }

    public BookAuthorEntity(BookEntity book, AuthorEntity author) {
        this.book = book;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "BookAuthorEntity{" +
                "id=" + id +
                ", book=" + book +
                ", author=" + author +
                '}';
    }
}
