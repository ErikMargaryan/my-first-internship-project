package com.myproject.myprojec.service.dto;

import com.myproject.myprojec.persistence.entity.BookAuthorEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookAuthorDto {

    private Long id;
    private BookDto books;
    private AuthorDto authors;

    public static BookAuthorDto mapEntityToDto(BookAuthorEntity entity) {
        if (entity == null) {
            return null;
        }
        BookAuthorDto dto = new BookAuthorDto();
        dto.setId(entity.getId());
//        BookEntity books = entity.getBooks();
//        if (books != null) {
//            dto.setBooks(books);
//        }
        return dto;
    }

    public static BookAuthorEntity mapDtoToEntity(BookAuthorDto dto) {
        if (dto == null) {
            return null;
        }
        BookAuthorEntity entity = new BookAuthorEntity();
        entity.setId(dto.getId());
        return entity;
    }
}
