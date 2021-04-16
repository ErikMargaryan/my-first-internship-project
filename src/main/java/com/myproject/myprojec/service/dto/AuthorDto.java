package com.myproject.myprojec.service.dto;

import com.myproject.myprojec.persistence.entity.AuthorEntity;
import com.myproject.myprojec.persistence.entity.BookAuthorEntity;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class AuthorDto {

    private Long id;
    private String name;
    private List<BookAuthorDto> bookAuthorDtoList;

    public AuthorDto() {
    }

    public AuthorDto(Long id, String name, List<BookAuthorDto> bookAuthorDtoList) {
        this.id = id;
        this.name = name;
        this.bookAuthorDtoList = bookAuthorDtoList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookAuthorDto> getBookAuthorDtoList() {
        return bookAuthorDtoList;
    }

    public void setBookAuthorDtoList(List<BookAuthorDto> bookAuthorDtoList) {
        this.bookAuthorDtoList = bookAuthorDtoList;
    }

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
