package com.myproject.myprojec.service.dto;

import com.myproject.myprojec.persistence.entity.BookAuthorEntity;
import com.myproject.myprojec.persistence.entity.BookEntity;
import com.myproject.myprojec.persistence.entity.BookGenreEntity;
import com.myproject.myprojec.persistence.entity.UserRatedBookEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private Long id;
    private String title;
    private List<BookAuthorDto> bookAuthorDtoList;
    private String isbn;
    private List<UserRatedBookDto> userRatedBookDtoList;
    private List<BookGenreDto> bookGenreDtoList;
    private String publisher;
    private Integer yearOfPublication;


    public static BookDto mapEntityToDto(BookEntity entity) {
        if (entity == null) {
            return null;
        }
        BookDto dto = new BookDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        List<BookAuthorEntity> bookAuthorEntityList = entity.getBookAuthorEntityList();
        if (!CollectionUtils.isEmpty(bookAuthorEntityList)) {
            dto.setBookAuthorDtoList(bookAuthorEntityList.stream().map(BookAuthorDto::mapEntityToDto).collect(Collectors.toList()));
        }
        dto.setIsbn(entity.getIsbn());
        List<UserRatedBookEntity> userRatedBookEntityList = entity.getUserRatedBookEntityList();
        if (!CollectionUtils.isEmpty(userRatedBookEntityList)) {
            dto.setUserRatedBookDtoList(userRatedBookEntityList.stream().map(UserRatedBookDto::mapEntityToDto).collect(Collectors.toList()));
        }
        List<BookGenreEntity> bookGenreEntityList = entity.getBookGenreEntityList();
        if (!CollectionUtils.isEmpty(bookGenreEntityList)) {
            dto.setBookGenreDtoList(bookGenreEntityList.stream().map(BookGenreDto::mapEntityToDto).collect(Collectors.toList()));
        }
        dto.setPublisher(entity.getPublisher());
        dto.setYearOfPublication(entity.getYearOfPublication());
        return dto;
    }

    public static BookEntity mapDtoToEntity(BookDto dto) {
        if (dto == null) {
            return null;
        }
        BookEntity entity = new BookEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        List<BookAuthorDto> bookAuthorDtoList = dto.getBookAuthorDtoList();
        if (!CollectionUtils.isEmpty(bookAuthorDtoList)) {
            entity.setBookAuthorEntityList(bookAuthorDtoList.stream().map(BookAuthorDto::mapDtoToEntity).collect(Collectors.toList()));
        }
        entity.setIsbn(dto.getIsbn());
        List<UserRatedBookDto> userRatedBookDtoList = dto.getUserRatedBookDtoList();
        if (!CollectionUtils.isEmpty(userRatedBookDtoList)) {
            entity.setUserRatedBookEntityList(userRatedBookDtoList.stream().map(UserRatedBookDto::mapDtoToEntity).collect(Collectors.toList()));
        }
        List<BookGenreDto> bookGenreDtoList = dto.getBookGenreDtoList();
        if (!CollectionUtils.isEmpty(bookGenreDtoList)) {
            entity.setBookGenreEntityList(bookGenreDtoList.stream().map(BookGenreDto::mapDtoToEntity).collect(Collectors.toList()));
        }
        entity.setPublisher(dto.getPublisher());
        entity.setYearOfPublication(dto.getYearOfPublication());

        return entity;
    }
}
