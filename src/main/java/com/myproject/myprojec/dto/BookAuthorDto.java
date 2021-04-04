package com.myproject.myprojec.dto;

import com.myproject.myprojec.model.entity.BookAuthorEntity;

public class BookAuthorDto {

    private Long id;
    private BookDto books;
    private AuthorDto authors;

    public BookAuthorDto() {
    }

    public BookAuthorDto(Long id, BookDto books, AuthorDto authors) {
        this.id = id;
        this.books = books;
        this.authors = authors;
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

    public AuthorDto getAuthors() {
        return authors;
    }

    public void setAuthors(AuthorDto authors) {
        this.authors = authors;
    }

    public static BookAuthorDto mapEntityToDto(BookAuthorEntity entity) {
        if (entity == null) {
            return null;
        }
        BookAuthorDto dto = new BookAuthorDto();
        dto.setId(entity.getId());
//        BookEntity books = entity.getBooks();
//        if (books != null) {
//            dto.setBooks(books);
//        }
        return dto;
    }
}
