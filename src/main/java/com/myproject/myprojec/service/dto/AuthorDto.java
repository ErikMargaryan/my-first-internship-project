package com.myproject.myprojec.service.dto;

import com.myproject.myprojec.persistence.entity.AuthorEntity;
import com.myproject.myprojec.persistence.entity.BookAuthorEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {

    private Long id;
    private String name;
    private List<BookAuthorDto> bookAuthorDtoList;

    public static AuthorDto mapEntityToDto(AuthorEntity entity) {
        if (entity == null) {
            return null;
        }
        AuthorDto dto = new AuthorDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        List<BookAuthorEntity> bookAuthorEntityList = entity.getBookAuthorEntityList();
        if (!CollectionUtils.isEmpty(bookAuthorEntityList)) {
            dto.setBookAuthorDtoList(bookAuthorEntityList.stream().map(BookAuthorDto::mapEntityToDto).collect(Collectors.toList()));
        }
        return dto;
    }

    public static AuthorEntity mapDtoToEntity(AuthorDto dto) {
        if (dto == null) {
            return null;
        }
        AuthorEntity entity = new AuthorEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        List<BookAuthorDto> bookAuthorDtoList = dto.getBookAuthorDtoList();
        if (!CollectionUtils.isEmpty(bookAuthorDtoList)) {
            entity.setBookAuthorEntityList(bookAuthorDtoList.stream().map(BookAuthorDto::mapDtoToEntity).collect(Collectors.toList()));
        }
        return entity;
    }
}
