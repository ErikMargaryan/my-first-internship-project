package com.myproject.myprojec.service.model.csv;

import com.myproject.myprojec.persistence.entity.BookEntity;
import com.opencsv.bean.CsvBindByName;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Book {
    @CsvBindByName(column = "Book-Title")
    private String title;
    @CsvBindByName(column = "ISBN")
    private String isbn;
    @CsvBindByName(column = "Year-Of-Publication")
    private Integer yearOfPublication;
    @CsvBindByName(column = "Publisher")
    private String publisher;

    public Book() {
    }

    public Book(String title, String isbn, Integer yearOfPublication, String publisher) {
        this.title = title;
        this.isbn = isbn;
        this.yearOfPublication = yearOfPublication;
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(Integer yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public static BookEntity mapCsvToEntity(Book csv) {
        BookEntity entity = new BookEntity();
        entity.setIsbn(csv.getIsbn());
        entity.setTitle(csv.getTitle());
        entity.setYearOfPublication(csv.getYearOfPublication());
        entity.setPublisher(csv.getPublisher());
        return entity;
    }

    public static List<BookEntity> mapCsvToEntityList(Collection<Book> bookCollection) {
        return bookCollection.stream()
                .map(Book::mapCsvToEntity)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                ", publisher='" + publisher + '\'' +
                '}';
    }
}
