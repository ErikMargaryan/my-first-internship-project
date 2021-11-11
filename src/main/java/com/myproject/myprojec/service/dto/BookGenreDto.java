package com.myproject.myprojec.service.dto;

import com.myproject.myprojec.persistence.entity.BookGenreEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookGenreDto {

    private Long id;
    private BookDto books;
    private GenreDto genres;

    public static BookGenreDto mapEntityToDto(BookGenreEntity entity) {
        if (entity == null) {
            return null;
        }
        BookGenreDto dto = new BookGenreDto();
        dto.setId(entity.getId());
        return dto;
    }

    public static BookGenreEntity mapDtoToEntity(BookGenreDto dto) {
        if (dto == null) {
            return null;
        }
        BookGenreEntity entity = new BookGenreEntity();
        entity.setId(dto.getId());
        return entity;
    }
}
