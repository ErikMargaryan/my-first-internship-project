package com.myproject.myprojec.mapper;

import com.myproject.myprojec.dto.GenresDto;
import com.myproject.myprojec.model.entity.GenresEntity;

public class GenresMapper {
    public static GenresDto mapEntityToDto(GenresEntity entity) {
        if (entity == null) {
            return null;
        }
        GenresDto dto = new GenresDto();
        dto.setId(entity.getId());
        dto.setGenres(entity.getGenres());
        return dto;
    }
}
