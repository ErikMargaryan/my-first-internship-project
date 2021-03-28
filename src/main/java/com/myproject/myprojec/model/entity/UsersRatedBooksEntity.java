package com.myproject.myprojec.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_rated_book")
public class UsersRatedBooksEntity {
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

    @Column(name = "isbn")
    private String ISBN;

    @Column(name = "book_rating")
    private int bookRating;

    public UsersRatedBooksEntity() {
    }

    public UsersRatedBooksEntity(Long id, BookEntity books, UserEntity users, String ISBN, int bookRating) {
        this.id = id;
        this.books = books;
        this.users = users;
        this.ISBN = ISBN;
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

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getBookRating() {
        return bookRating;
    }

    public void setBookRating(int bookRating) {
        this.bookRating = bookRating;
    }

    @Override
    public String toString() {
        return "UsersRatedBooksEntity{" +
                "id=" + id +
                ", ISBN='" + ISBN + '\'' +
                ", bookRating=" + bookRating +
                '}';
    }
}
