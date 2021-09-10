package com.myproject.myprojec.controller.dto;

import com.myproject.myprojec.persistence.entity.BookGenreEntity;
import com.myproject.myprojec.persistence.entity.GenreEntity;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

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

    public static GenreDto mapEntityToDto(GenreEntity entity) {
        if (entity == null) {
            return null;
        }
        GenreDto dto = new GenreDto();
        dto.setId(entity.getId());
        dto.setGenres(entity.getGenres());
        List<BookGenreEntity> bookGenreEntityList = entity.getBookGenreEntityList();
        if (!CollectionUtils.isEmpty(bookGenreEntityList)) {
            dto.setBookGenreDtoList(bookGenreEntityList.stream().map(BookGenreDto::mapEntityToDto).collect(Collectors.toList()));
        }
        return dto;
    }

    public static GenreEntity mapDtoToEntity(GenreDto dto) {
        if (dto == null) {
            return null;
        }
        GenreEntity entity = new GenreEntity();
        entity.setId(entity.getId());
        entity.setGenres(entity.getGenres());
        List<BookGenreDto> bookGenreDtoList = dto.getBookGenreDtoList();
        if (!CollectionUtils.isEmpty(bookGenreDtoList)) {
            entity.setBookGenreEntityList(bookGenreDtoList.stream().map(BookGenreDto::mapDtoToEntity).collect(Collectors.toList()));
        }
        return entity;
    }
}
