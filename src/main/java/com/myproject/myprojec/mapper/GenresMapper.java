package com.myproject.myprojec.mapper;

import com.myproject.myprojec.dto.GenresDto;
import com.myproject.myprojec.model.entity.BookGenreEntity;
import com.myproject.myprojec.model.entity.GenresEntity;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class GenresMapper {
    public static GenresDto mapEntityToDto(GenresEntity entity) {
        if (entity == null) {
            return null;
        }
        GenresDto dto = new GenresDto();
        dto.setId(entity.getId());
        dto.setGenres(entity.getGenres());
        List<BookGenreEntity> bookGenreEntityList = entity.getBookGenreEntityList();
        if (!CollectionUtils.isEmpty(bookGenreEntityList)) {
            dto.setBookGenreDtoList(bookGenreEntityList.stream().map(BookGenreMapper::mapEntityToDto).collect(Collectors.toList()));
        }
        return dto;
    }
}
