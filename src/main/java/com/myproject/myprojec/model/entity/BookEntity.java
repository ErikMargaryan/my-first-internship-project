package com.myproject.myprojec.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @OneToMany(mappedBy = "books")
    private List<BookAuthorEntity> bookAuthorEntityList;
    @Column(name = "price", nullable = false)
    private double price;
    @OneToMany(mappedBy = "books")
    private List<UsersRatedBooksEntity> usersRatedBooksEntityList;
    @OneToMany(mappedBy = "books")
    private List<BookGenreEntity> bookGenreEntityList;


    public BookEntity() {
    }

    public BookEntity(Long id, String title, List<BookAuthorEntity> bookAuthorEntityList, double price, List<UsersRatedBooksEntity> usersRatedBooksEntityList, List<BookGenreEntity> bookGenreEntityList) {
        this.id = id;
        this.title = title;
        this.bookAuthorEntityList = bookAuthorEntityList;
        this.price = price;
        this.usersRatedBooksEntityList = usersRatedBooksEntityList;
        this.bookGenreEntityList = bookGenreEntityList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<BookAuthorEntity> getBookAuthorEntityList() {
        return bookAuthorEntityList;
    }

    public void setBookAuthorEntityList(List<BookAuthorEntity> bookAuthorEntityList) {
        this.bookAuthorEntityList = bookAuthorEntityList;
    }

    public List<UsersRatedBooksEntity> getUsersRatedBooksList() {
        return usersRatedBooksEntityList;
    }

    public void setUsersRatedBooksList(List<UsersRatedBooksEntity> usersRatedBooksEntityList) {
        this.usersRatedBooksEntityList = usersRatedBooksEntityList;
    }

    public List<BookGenreEntity> getBookGenreEntityList() {
        return bookGenreEntityList;
    }

    public void setBookGenreEntityList(List<BookGenreEntity> bookGenreEntityList) {
        this.bookGenreEntityList = bookGenreEntityList;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
