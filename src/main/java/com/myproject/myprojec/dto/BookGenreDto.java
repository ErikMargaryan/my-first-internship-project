package com.myproject.myprojec.dto;

public class BookGenreDto {

    private Long id;
    private BookDto books;
    private GenreDto genres;

    public BookGenreDto() {
    }

    public BookGenreDto(Long id, BookDto books, GenreDto genres) {
        this.id = id;
        this.books = books;
        this.genres = genres;
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

    public GenreDto getGenres() {
        return genres;
    }

    public void setGenres(GenreDto genres) {
        this.genres = genres;
    }
}
