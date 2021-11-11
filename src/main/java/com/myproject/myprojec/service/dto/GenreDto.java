package com.myproject.myprojec.service.dto;

import com.myproject.myprojec.persistence.entity.BookGenreEntity;
import com.myproject.myprojec.persistence.entity.GenreEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreDto {

    private Long id;
    private String genres;
    private List<BookGenreDto> bookGenreDtoList;

    public static GenreDto mapEntityToDto(GenreEntity entity) {
        if (entity == null) {
            return null;
        }
        GenreDto dto = new GenreDto();
        dto.setId(entity.getId());
        dto.setGenres(entity.getGenre());
        List<BookGenreEntity> bookGenreEntityList = entity.getBookGenreEntityList();
        if (!CollectionUtils.isEmpty(bookGenreEntityList)) {
            dto.setBookGenreDtoList(bookGenreEntityList.stream().map(BookGenreDto::mapEntityToDto).collect(Collectors.toList()));
        }
        return dto;
    }

    public static GenreEntity mapDtoToEntity(GenreDto dto) {
        if (dto == null) {
            return null;
        }
        GenreEntity entity = new GenreEntity();
        entity.setId(entity.getId());
        entity.setGenre(entity.getGenre());
        List<BookGenreDto> bookGenreDtoList = dto.getBookGenreDtoList();
        if (!CollectionUtils.isEmpty(bookGenreDtoList)) {
            entity.setBookGenreEntityList(bookGenreDtoList.stream().map(BookGenreDto::mapDtoToEntity).collect(Collectors.toList()));
        }
        return entity;
    }
}
