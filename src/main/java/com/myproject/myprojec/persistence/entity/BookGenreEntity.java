package com.myproject.myprojec.persistence.entity;

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
    private GenreEntity genres;

    public BookGenreEntity() {
    }

    public BookGenreEntity(Long id, BookEntity books, GenreEntity genres) {
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

    public GenreEntity getGenres() {
        return genres;
    }

    public void setGenres(GenreEntity genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "BookGenreEntity{" +
                "id=" + id +
                ", books=" + books +
                ", genres=" + genres +
                '}';
    }
}
