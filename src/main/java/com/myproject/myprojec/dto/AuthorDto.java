package com.myproject.myprojec.dto;

import com.myproject.myprojec.persistence.entity.AuthorEntity;
import com.myproject.myprojec.persistence.entity.BookAuthorEntity;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class AuthorDto {

    private Long id;
    private String firstName;
    private String lastName;
    private List<BookAuthorDto> bookAuthorDtoList;

    public AuthorDto() {
    }

    public AuthorDto(Long id, String firstName, String lastName, List<BookAuthorDto> bookAuthorDtoList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookAuthorDtoList = bookAuthorDtoList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
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
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        List<BookAuthorDto> bookAuthorDtoList = dto.getBookAuthorDtoList();
        if (!CollectionUtils.isEmpty(bookAuthorDtoList)) {
            entity.setBookAuthorEntityList(bookAuthorDtoList.stream().map(BookAuthorDto::mapDtoToEntity).collect(Collectors.toList()));
        }
        return entity;
    }
}
