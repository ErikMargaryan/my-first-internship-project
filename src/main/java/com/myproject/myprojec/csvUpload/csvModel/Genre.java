package com.myproject.myprojec.csvUpload.csvModel;

import com.myproject.myprojec.persistence.entity.GenreEntity;
import com.opencsv.bean.CsvBindByName;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Genre {

    private Long id;
    @CsvBindByName(column = "Genre")
    private String genres;

    public Genre() {
    }

    public Genre(Long id, String genres) {
        this.id = id;
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

    public static GenreEntity mapCsvToEntity(Genre csv) {
        GenreEntity entity = new GenreEntity();
        entity.setId(csv.getId());
        entity.setGenres(csv.getGenres());
        return entity;
    }

    public static List<GenreEntity> mapCsvToEntityList(Collection<Genre> userDetailCollection) {
        return userDetailCollection.stream()
                .map(Genre::mapCsvToEntity)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", genres='" + genres + '\'' +
                '}';
    }
}
