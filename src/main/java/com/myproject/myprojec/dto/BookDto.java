package com.myproject.myprojec.dto;

import com.myproject.myprojec.mapper.BookAuthorMapper;
import com.myproject.myprojec.mapper.BookGenreMapper;
import com.myproject.myprojec.mapper.UsersRatedBooksMapper;
import com.myproject.myprojec.model.entity.BookAuthorEntity;
import com.myproject.myprojec.model.entity.BookEntity;
import com.myproject.myprojec.model.entity.BookGenreEntity;
import com.myproject.myprojec.model.entity.UserRatedBookEntity;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BookDto {

    private Long id;
    private String title;
    private List<BookAuthorDto> bookAuthorDtoList;
    private Double isbn;
    private List<UserRatedBookDto> userRatedBookDtoList;
    private List<BookGenreDto> bookGenreDtoList;
    private String publisher;
    private int yearOfPublication;

    public BookDto() {
    }

    public BookDto(Long id, String title, List<BookAuthorDto> bookAuthorDtoList, Double isbn, List<UserRatedBookDto> userRatedBookDtoList, List<BookGenreDto> bookGenreDtoList, String publisher, int yearOfPublication) {
        this.id = id;
        this.title = title;
        this.bookAuthorDtoList = bookAuthorDtoList;
        this.isbn = isbn;
        this.userRatedBookDtoList = userRatedBookDtoList;
        this.bookGenreDtoList = bookGenreDtoList;
        this.publisher = publisher;
        this.yearOfPublication = yearOfPublication;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<BookAuthorDto> getBookAuthorDtoList() {
        return bookAuthorDtoList;
    }

    public void setBookAuthorDtoList(List<BookAuthorDto> bookAuthorDtoList) {
        this.bookAuthorDtoList = bookAuthorDtoList;
    }

    public Double getIsbn() {
        return isbn;
    }

    public void setIsbn(Double isbn) {
        this.isbn = isbn;
    }

    public List<UserRatedBookDto> getUsersRatedBooksDtoList() {
        return userRatedBookDtoList;
    }

    public void setUsersRatedBooksDtoList(List<UserRatedBookDto> userRatedBookDtoList) {
        this.userRatedBookDtoList = userRatedBookDtoList;
    }

    public List<BookGenreDto> getBookGenreDtoList() {
        return bookGenreDtoList;
    }

    public void setBookGenreDtoList(List<BookGenreDto> bookGenreDtoList) {
        this.bookGenreDtoList = bookGenreDtoList;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

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
        List<UserRatedBookEntity> userRatedBookEntityList = entity.getUsersRatedBooksList();
        if (!CollectionUtils.isEmpty(userRatedBookEntityList)) {
            dto.setUsersRatedBooksDtoList(userRatedBookEntityList.stream().map(UserRatedBookDto::mapEntityToDto).collect(Collectors.toList()));
        }
        List<BookGenreEntity> bookGenreEntityList = entity.getBookGenreEntityList();
        if (!CollectionUtils.isEmpty(bookGenreEntityList)) {
            dto.setBookGenreDtoList(bookGenreEntityList.stream().map(BookGenreDto::mapEntityToDto).collect(Collectors.toList()));
        }
        dto.setPublisher(entity.getPublisher());
        dto.setYearOfPublication(entity.getYearOfPublication());
        return dto;
    }
}
