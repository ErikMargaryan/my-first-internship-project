package com.myproject.myprojec.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "book_genre")
public class BookGenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity books;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private GenresEntity genres;

    public BookGenreEntity() {
    }

    public BookGenreEntity(Long id, BookEntity books, GenresEntity genres) {
        this.id = id;
        this.books = books;
        this.genres = genres;
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

    public GenresEntity getGenres() {
        return genres;
    }

    public void setGenres(GenresEntity genres) {
        this.genres = genres;
    }
}
