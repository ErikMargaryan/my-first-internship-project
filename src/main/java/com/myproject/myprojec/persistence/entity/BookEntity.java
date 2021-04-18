package com.myproject.myprojec.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "book")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @OneToMany(mappedBy = "books")
    private List<BookAuthorEntity> bookAuthorEntityList;
    @Column(name = "isbn", nullable = false)
    private String isbn;
    @OneToMany(mappedBy = "books")
    private List<UserRatedBookEntity> userRatedBookEntityList;
    @OneToMany(mappedBy = "books")
    private List<BookGenreEntity> bookGenreEntityList;
    @Column(name = "publisher", nullable = false)
    private String publisher;
    @Column(name = "year_of_publication", nullable = false)
    private Integer yearOfPublication;

    public BookEntity() {
    }

    public BookEntity(Long id, String title, List<BookAuthorEntity> bookAuthorEntityList,
                      String isbn, List<UserRatedBookEntity> userRatedBookEntityList,
                      List<BookGenreEntity> bookGenreEntityList, String publisher, Integer yearOfPublication) {
        this.id = id;
        this.title = title;
        this.bookAuthorEntityList = bookAuthorEntityList;
        this.isbn = isbn;
        this.userRatedBookEntityList = userRatedBookEntityList;
        this.bookGenreEntityList = bookGenreEntityList;
        this.publisher = publisher;
        this.yearOfPublication = yearOfPublication;
    }

//    public BookEntity(String title, List<BookAuthorEntity> bookAuthorEntityList,
//                      String isbn, List<UserRatedBookEntity> userRatedBookEntityList,
//                      List<BookGenreEntity> bookGenreEntityList, String publisher, Integer yearOfPublication) {
//        this.title = title;
//        this.bookAuthorEntityList = bookAuthorEntityList;
//        this.isbn = isbn;
//        this.userRatedBookEntityList = userRatedBookEntityList;
//        this.bookGenreEntityList = bookGenreEntityList;
//        this.publisher = publisher;
//        this.yearOfPublication = yearOfPublication;
//    }

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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<BookAuthorEntity> getBookAuthorEntityList() {
        return bookAuthorEntityList;
    }

    public void setBookAuthorEntityList(List<BookAuthorEntity> bookAuthorEntityList) {
        this.bookAuthorEntityList = bookAuthorEntityList;
    }

    public List<UserRatedBookEntity> getUsersRatedBooksList() {
        return userRatedBookEntityList;
    }

    public void setUsersRatedBooksList(List<UserRatedBookEntity> userRatedBookEntityList) {
        this.userRatedBookEntityList = userRatedBookEntityList;
    }

    public List<BookGenreEntity> getBookGenreEntityList() {
        return bookGenreEntityList;
    }

    public void setBookGenreEntityList(List<BookGenreEntity> bookGenreEntityList) {
        this.bookGenreEntityList = bookGenreEntityList;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(Integer yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", ISBN=" + isbn +
                ", publisher='" + publisher + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                '}';
    }

}
