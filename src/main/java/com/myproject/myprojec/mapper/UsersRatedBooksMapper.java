package com.myproject.myprojec.mapper;

import com.myproject.myprojec.dto.UsersRatedBooksDto;
import com.myproject.myprojec.model.entity.UsersRatedBooksEntity;

public class UsersRatedBooksMapper {
    public static UsersRatedBooksDto mapEntityToDto(UsersRatedBooksEntity entity) {
        if (entity == null) {
            return null;
        }
        UsersRatedBooksDto dto = new UsersRatedBooksDto();
        dto.setId(entity.getId());
        dto.setISBN(entity.getISBN());
        dto.setBookRating(entity.getBookRating());
        return dto;
    }
}
