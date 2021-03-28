package com.myproject.myprojec.mapper;

import com.myproject.myprojec.dto.AuthorDto;
import com.myproject.myprojec.dto.BookAuthorDto;
import com.myproject.myprojec.model.entity.AuthorEntity;
import com.myproject.myprojec.model.entity.BookAuthorEntity;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class AuthorMapper {
    public static AuthorDto mapEntityToDto(AuthorEntity entity) {
        if (entity == null) {
            return null;
        }
        AuthorDto dto = new AuthorDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
//        List<BookAuthorEntity> bookAuthorEntityList = entity.getBookAuthorEntityList();
//        if (!CollectionUtils.isEmpty(bookAuthorEntityList)) {
//            dto.setBookAuthorDtoList(bookAuthorEntityList.stream().map(AuthorMapper::mapEntityToDto).collect(Collectors.toList()));
//        }
        return dto;
    }
}
