package com.myproject.myprojec.mapper;

import com.myproject.myprojec.dto.BookAuthorDto;
import com.myproject.myprojec.model.entity.AuthorEntity;
import com.myproject.myprojec.model.entity.BookAuthorEntity;
import com.myproject.myprojec.model.entity.BookEntity;
import org.springframework.cglib.core.CollectionUtils;

import java.util.List;

public class BookAuthorMapper {

    public static BookAuthorDto mapEntityToDto(BookAuthorEntity entity) {
        if (entity == null) {
            return null;
        }
        BookAuthorDto dto = new BookAuthorDto();
        dto.setId(entity.getId());
        //???
        return dto;
    }
}
