package com.myproject.myprojec.service.dto;

import com.myproject.myprojec.persistence.entity.UserRatedBookEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRatedBookDto {

    private Long id;
    private BookDto books;
    private UserDto users;
    private Integer bookRating;

    public static UserRatedBookDto mapEntityToDto(UserRatedBookEntity entity) {
        if (entity == null) {
            return null;
        }
        UserRatedBookDto dto = new UserRatedBookDto();
        dto.setId(entity.getId());
        dto.setBookRating(entity.getBookRating());
        return dto;
    }

    public static UserRatedBookEntity mapDtoToEntity(UserRatedBookDto dto) {
        if (dto == null) {
            return null;
        }
        UserRatedBookEntity entity = new UserRatedBookEntity();
        entity.setId(dto.getId());
        entity.setBookRating(dto.getBookRating());
        return entity;
    }
}
