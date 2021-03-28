package com.myproject.myprojec.mapper;

import com.myproject.myprojec.dto.BookGenreDto;
import com.myproject.myprojec.model.entity.BookGenreEntity;

public class BookGenreMapper {
    public BookGenreDto mapEntityToDto(BookGenreEntity entity) {
        if (entity == null) {
            return null;
        }
        BookGenreDto dto = new BookGenreDto();
        dto.setId(entity.getId());
        return dto;
    }
}
