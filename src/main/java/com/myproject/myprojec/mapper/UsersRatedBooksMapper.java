package com.myproject.myprojec.mapper;

import com.myproject.myprojec.dto.UserRatedBookDto;
import com.myproject.myprojec.model.entity.UserRatedBookEntity;

public class UsersRatedBooksMapper {
    public static UserRatedBookDto mapEntityToDto(UserRatedBookEntity entity) {
        if (entity == null) {
            return null;
        }
        UserRatedBookDto dto = new UserRatedBookDto();
        dto.setId(entity.getId());
        dto.setBookRating(entity.getBookRating());
        return dto;
    }
}
