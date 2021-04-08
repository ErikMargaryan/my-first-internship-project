package com.myproject.myprojec.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_rated_book")
public class UserRatedBookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity books;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity users;

    @Column(name = "book_rating")
    private Integer bookRating;

    public UserRatedBookEntity() {
    }

    public UserRatedBookEntity(Long id, BookEntity books, UserEntity users, Integer bookRating) {
        this.id = id;
        this.books = books;
        this.users = users;
        this.bookRating = bookRating;
    }

    //For upload
    public UserRatedBookEntity(Long id, Integer bookRating) {
        this.id = id;
        this.bookRating = bookRating;
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

    public UserEntity getUsers() {
        return users;
    }

    public void setUsers(UserEntity users) {
        this.users = users;
    }

    public Integer getBookRating() {
        return bookRating;
    }

    public void setBookRating(Integer bookRating) {
        this.bookRating = bookRating;
    }

    @Override
    public String toString() {
        return "UsersRatedBooksEntity{" +
                "id=" + id +
                ", bookRating=" + bookRating +
                '}';
    }
}
