package com.myproject.myprojec.dto;

import java.util.List;

public class GenreDto {

    private Long id;
    private String genres;
    private List<BookGenreDto> bookGenreDtoList;

    public GenreDto() {
    }

    public GenreDto(Long id, String genres, List<BookGenreDto> bookGenreDtoList) {
        this.id = id;
        this.genres = genres;
        this.bookGenreDtoList = bookGenreDtoList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public List<BookGenreDto> getBookGenreDtoList() {
        return bookGenreDtoList;
    }

    public void setBookGenreDtoList(List<BookGenreDto> bookGenreDtoList) {
        this.bookGenreDtoList = bookGenreDtoList;
    }
}
