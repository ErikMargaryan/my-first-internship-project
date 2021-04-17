package com.myproject.myprojec.service.model.csv;

import com.myproject.myprojec.persistence.entity.UserRatedBookEntity;
import com.opencsv.bean.CsvBindByName;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserRatedBook {
    @CsvBindByName(column = "Book-Rating")
    private Integer bookRating;

    public UserRatedBook() {
    }

    public UserRatedBook(Integer bookRating) {
        this.bookRating = bookRating;
    }

    public Integer getBookRating() {
        return bookRating;
    }

    public void setBookRating(Integer bookRating) {
        this.bookRating = bookRating;
    }

    public static UserRatedBookEntity mapCsvToEntity(UserRatedBook csv) {
        UserRatedBookEntity entity = new UserRatedBookEntity();
        entity.setBookRating(csv.getBookRating());
        return entity;
    }

    public static List<UserRatedBookEntity> mapCsvToEntityList(Collection<UserRatedBook> userRatedBookCollection) {
        return userRatedBookCollection.stream()
                .map(UserRatedBook::mapCsvToEntity)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "UserRatedBook{" +
                "bookRating=" + bookRating +
                '}';
    }
}
