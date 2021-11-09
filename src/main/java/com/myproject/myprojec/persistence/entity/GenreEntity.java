package com.myproject.myprojec.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genre")
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "list_of_genres")
    private String genre;

    @OneToMany(mappedBy = "genre", targetEntity = BookGenreEntity.class)
    private List<BookGenreEntity> bookGenreEntityList;

    public GenreEntity() {
    }

    public GenreEntity(Long id, String genre, List<BookGenreEntity> bookGenreEntityList) {
        this.id = id;
        this.genre = genre;
        this.bookGenreEntityList = bookGenreEntityList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<BookGenreEntity> getBookGenreEntityList() {
        return bookGenreEntityList;
    }

    public void setBookGenreEntityList(List<BookGenreEntity> bookGenreEntityList) {
        this.bookGenreEntityList = bookGenreEntityList;
    }

    @Override
    public String toString() {
        return "GenresEntity{" +
                "id=" + id +
                ", genre=" + genre +
                '}';
    }
}
