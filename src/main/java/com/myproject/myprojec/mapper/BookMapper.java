package com.myproject.myprojec.mapper;

import com.myproject.myprojec.dto.BookDto;
import com.myproject.myprojec.model.entity.BookEntity;

public class BookMapper {
    public static BookDto mapEntityToDto(BookEntity entity) {
        if (entity == null) {
            return null;
        }
        BookDto dto = new BookDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setPrice(entity.getPrice());
        return dto;
    }
}
