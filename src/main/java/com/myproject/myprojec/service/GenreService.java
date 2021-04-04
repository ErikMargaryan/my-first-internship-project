package com.myproject.myprojec.service;

import com.myproject.myprojec.dto.GenreDto;
import com.myproject.myprojec.mapper.GenresMapper;
import com.myproject.myprojec.model.entity.GenreEntity;
import com.myproject.myprojec.rpository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public GenreDto createGenres(GenreDto dto) {
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setGenres(dto.getGenres());
        genreEntity = genreRepository.save(genreEntity);
        return GenresMapper.mapEntityToDto(genreEntity);
    }

    public GenreDto getGenres(Long id) throws Exception {
        GenreEntity genreEntity = genreRepository.findById(id)
                .orElseThrow(() -> new Exception("Genres not found about that name"));
        return GenresMapper.mapEntityToDto(genreEntity);
    }

    public GenreDto updateGenres(Long id, GenreDto dto) throws Exception {
        GenreEntity genreEntity = genreRepository.findById(id)
                .orElseThrow(() -> new Exception("Genres not found about that name"));
        if (dto.getGenres() != null) {
            genreEntity.setGenres(dto.getGenres());
        }
        genreEntity = genreRepository.save(genreEntity);
        return GenresMapper.mapEntityToDto(genreEntity);
    }

    public void deleteGenres(Long id) {
        genreRepository.deleteById(id);
    }
}
