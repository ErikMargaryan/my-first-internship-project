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
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private BookEntity book;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @Column(name = "book_rating")
    private Integer bookRating;

    public UserRatedBookEntity() {
    }

    public UserRatedBookEntity(Long id, BookEntity book, UserEntity user, Integer bookRating) {
        this.id = id;
        this.book = book;
        this.user = user;
        this.bookRating = bookRating;
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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
