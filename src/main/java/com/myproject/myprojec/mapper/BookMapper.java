package com.myproject.myprojec.mapper;

import com.myproject.myprojec.dto.BookDto;
import com.myproject.myprojec.model.entity.BookAuthorEntity;
import com.myproject.myprojec.model.entity.BookEntity;
import com.myproject.myprojec.model.entity.BookGenreEntity;
import com.myproject.myprojec.model.entity.UserRatedBookEntity;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BookMapper {
    public static BookDto mapEntityToDto(BookEntity entity) {
        if (entity == null) {
            return null;
        }
        BookDto dto = new BookDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        List<BookAuthorEntity> bookAuthorEntityList = entity.getBookAuthorEntityList();
        if (!CollectionUtils.isEmpty(bookAuthorEntityList)) {
            dto.setBookAuthorDtoList(bookAuthorEntityList.stream().map(BookAuthorMapper::mapEntityToDto).collect(Collectors.toList()));
        }
        dto.setIsbn(entity.getIsbn());
        List<UserRatedBookEntity> userRatedBookEntityList = entity.getUsersRatedBooksList();
        if (!CollectionUtils.isEmpty(userRatedBookEntityList)) {
            dto.setUsersRatedBooksDtoList(userRatedBookEntityList.stream().map(UsersRatedBooksMapper::mapEntityToDto).collect(Collectors.toList()));
        }
        List<BookGenreEntity> bookGenreEntityList = entity.getBookGenreEntityList();
        if (!CollectionUtils.isEmpty(bookGenreEntityList)) {
            dto.setBookGenreDtoList(bookGenreEntityList.stream().map(BookGenreMapper::mapEntityToDto).collect(Collectors.toList()));
        }
        dto.setPublisher(entity.getPublisher());
        dto.setYearOfPublication(entity.getYearOfPublication());
        return dto;
    }
}
