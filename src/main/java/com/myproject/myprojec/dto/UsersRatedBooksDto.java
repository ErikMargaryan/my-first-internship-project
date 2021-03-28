package com.myproject.myprojec.dto;

public class UsersRatedBooksDto {

    private Long id;
    private BookDto books;
    private UserDto users;
    private String ISBN;
    private int bookRating;

    public UsersRatedBooksDto() {
    }

    public UsersRatedBooksDto(Long id, BookDto books, UserDto users, String ISBN, int bookRating) {
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

    public BookDto getBooks() {
        return books;
    }

    public void setBooks(BookDto books) {
        this.books = books;
    }

    public UserDto getUsers() {
        return users;
    }

    public void setUsers(UserDto users) {
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
}
