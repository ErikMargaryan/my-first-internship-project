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
    private String genres;

    @OneToMany(mappedBy = "genres")
    private List<BookGenreEntity> bookGenreEntityList;

    public GenreEntity() {
    }

    public GenreEntity(Long id, String genres, List<BookGenreEntity> bookGenreEntityList) {
        this.id = id;
        this.genres = genres;
        this.bookGenreEntityList = bookGenreEntityList;
    }

    public GenreEntity(String genres) {
        this.genres = genres;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
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
                ", genres=" + genres +
                '}';
    }
}
