package com.myproject.myprojec.dto;

import java.util.List;

public class AuthorDto {

    private Long id;
    private String firstName;
    private String lastName;
    private List<BookAuthorDto> bookAuthorDtoList;

    public AuthorDto() {
    }

    public AuthorDto(Long id, String firstName, String lastName, List<BookAuthorDto> bookAuthorDtoList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookAuthorDtoList = bookAuthorDtoList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<BookAuthorDto> getBookAuthorDtoList() {
        return bookAuthorDtoList;
    }

    public void setBookAuthorDtoList(List<BookAuthorDto> bookAuthorDtoList) {
        this.bookAuthorDtoList = bookAuthorDtoList;
    }
}
