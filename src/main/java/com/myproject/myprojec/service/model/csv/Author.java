package com.myproject.myprojec.service.model.csv;

import com.myproject.myprojec.persistence.entity.AuthorEntity;
import com.opencsv.bean.CsvBindByName;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Author {
    @CsvBindByName(column = "Book-Author")
    private String name;

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static AuthorEntity mapCsvToEntity(Author csv) {
        AuthorEntity entity = new AuthorEntity();
        entity.setName(csv.getName());
        return entity;
    }

    public static List<AuthorEntity> mapCsvToEntityList(Collection<Author> authorCollection) {
        return authorCollection.stream()
                .map(Author::mapCsvToEntity)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                '}';
    }
}
