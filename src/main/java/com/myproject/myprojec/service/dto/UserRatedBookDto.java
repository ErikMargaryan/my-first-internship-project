package com.myproject.myprojec.service.dto;

import com.myproject.myprojec.persistence.entity.UserRatedBookEntity;

public class UserRatedBookDto {

    private Long id;
    private BookDto books;
    private UserDto users;
    private Integer bookRating;

    public UserRatedBookDto() {
    }

    public UserRatedBookDto(Long id, BookDto books, UserDto users, Integer bookRating) {
        this.id = id;
        this.books = books;
        this.users = users;
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

    public Integer getBookRating() {
        return bookRating;
    }

    public void setBookRating(Integer bookRating) {
        this.bookRating = bookRating;
    }

    public static UserRatedBookDto mapEntityToDto(UserRatedBookEntity entity) {
        if (entity == null) {
            return null;
        }
        UserRatedBookDto dto = new UserRatedBookDto();
        dto.setId(entity.getId());
        dto.setBookRating(entity.getBookRating());
        return dto;
    }

    public static UserRatedBookEntity mapDtoToEntity(UserRatedBookDto dto) {
        if (dto == null) {
            return null;
        }
        UserRatedBookEntity entity = new UserRatedBookEntity();
        entity.setId(dto.getId());
        entity.setBookRating(dto.getBookRating());
        return entity;
    }
}
