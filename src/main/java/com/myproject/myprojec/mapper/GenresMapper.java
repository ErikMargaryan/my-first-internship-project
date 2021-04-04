package com.myproject.myprojec.mapper;

import com.myproject.myprojec.dto.GenreDto;
import com.myproject.myprojec.model.entity.BookGenreEntity;
import com.myproject.myprojec.model.entity.GenreEntity;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class GenresMapper {
    public static GenreDto mapEntityToDto(GenreEntity entity) {
        if (entity == null) {
            return null;
        }
        GenreDto dto = new GenreDto();
        dto.setId(entity.getId());
        dto.setGenres(entity.getGenres());
        List<BookGenreEntity> bookGenreEntityList = entity.getBookGenreEntityList();
        if (!CollectionUtils.isEmpty(bookGenreEntityList)) {
            dto.setBookGenreDtoList(bookGenreEntityList.stream().map(BookGenreMapper::mapEntityToDto).collect(Collectors.toList()));
        }
        return dto;
    }
}
