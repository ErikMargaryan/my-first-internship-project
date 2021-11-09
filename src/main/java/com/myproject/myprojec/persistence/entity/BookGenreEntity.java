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
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private BookEntity book;

    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private GenreEntity genre;

    public BookGenreEntity() {
    }

    public BookGenreEntity(Long id, BookEntity book, GenreEntity genre) {
        this.id = id;
        this.book = book;
        this.genre = genre;
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

    public GenreEntity getGenre() {
        return genre;
    }

    public void setGenre(GenreEntity genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "BookGenreEntity{" +
                "id=" + id +
                ", book=" + book +
                ", genre=" + genre +
                '}';
    }
}
