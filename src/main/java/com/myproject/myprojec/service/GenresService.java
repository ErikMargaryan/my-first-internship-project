package com.myproject.myprojec.service;

import com.myproject.myprojec.dto.GenresDto;
import com.myproject.myprojec.mapper.GenresMapper;
import com.myproject.myprojec.model.entity.GenresEntity;
import com.myproject.myprojec.rpository.GenresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenresService {

    private final GenresRepository genresRepository;

    @Autowired
    public GenresService(GenresRepository genresRepository) {
        this.genresRepository = genresRepository;
    }

    public GenresDto createGenres(GenresDto dto) {
        GenresEntity genresEntity = new GenresEntity();
        genresEntity.setGenres(dto.getGenres());
        genresEntity = genresRepository.save(genresEntity);
        return GenresMapper.mapEntityToDto(genresEntity);
    }

    public GenresDto getGenres(Long id) throws Exception {
        GenresEntity genresEntity = genresRepository.findById(id)
                .orElseThrow(() -> new Exception("Genres not found about that name"));
        return GenresMapper.mapEntityToDto(genresEntity);
    }

    public GenresDto updateGenres(Long id, GenresDto dto) throws Exception {
        GenresEntity genresEntity = genresRepository.findById(id)
                .orElseThrow(() -> new Exception("Genres not found about that name"));
        if (dto.getGenres() != null) {
            genresEntity.setGenres(dto.getGenres());
        }
        genresEntity = genresRepository.save(genresEntity);
        return GenresMapper.mapEntityToDto(genresEntity);
    }

    public void deleteGenres(Long id) {
        genresRepository.deleteById(id);
    }
}
