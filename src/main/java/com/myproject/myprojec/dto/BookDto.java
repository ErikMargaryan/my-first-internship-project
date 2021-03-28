package com.myproject.myprojec.dto;

import java.util.List;

public class BookDto {

    private Long id;
    private String title;
    private List<BookAuthorDto> bookAuthorDtoList;
    private double price;
    private List<UsersRatedBooksDto> usersRatedBooksDtoList;
    private List<BookGenreDto> bookGenreDtoList;

    public BookDto() {
    }

    public BookDto(Long id, String title, List<BookAuthorDto> bookAuthorDtoList, double price, List<UsersRatedBooksDto> usersRatedBooksDtoList, List<BookGenreDto> bookGenreDtoList) {
        this.id = id;
        this.title = title;
        this.bookAuthorDtoList = bookAuthorDtoList;
        this.price = price;
        this.usersRatedBooksDtoList = usersRatedBooksDtoList;
        this.bookGenreDtoList = bookGenreDtoList;
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

    public List<BookAuthorDto> getBookAuthorDtoList() {
        return bookAuthorDtoList;
    }

    public void setBookAuthorDtoList(List<BookAuthorDto> bookAuthorDtoList) {
        this.bookAuthorDtoList = bookAuthorDtoList;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<UsersRatedBooksDto> getUsersRatedBooksDtoList() {
        return usersRatedBooksDtoList;
    }

    public void setUsersRatedBooksDtoList(List<UsersRatedBooksDto> usersRatedBooksDtoList) {
        this.usersRatedBooksDtoList = usersRatedBooksDtoList;
    }

    public List<BookGenreDto> getBookGenreDtoList() {
        return bookGenreDtoList;
    }

    public void setBookGenreDtoList(List<BookGenreDto> bookGenreDtoList) {
        this.bookGenreDtoList = bookGenreDtoList;
    }
}
