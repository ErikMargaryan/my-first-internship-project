package com.myproject.myprojec.service;

import com.myproject.myprojec.csvUpload.csvHelper.GenreHelper;
import com.myproject.myprojec.dto.GenreDto;
import com.myproject.myprojec.persistence.entity.GenreEntity;
import com.myproject.myprojec.persistence.rpository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public GenreDto createGenres(GenreDto dto) {
        GenreEntity genreEntity = new GenreEntity();
//        genreEntity.setGenres(dto.getGenres());
//        genreEntity = genreRepository.save(genreEntity);
        GenreDto.mapDtoToEntity(dto);
        return GenreDto.mapEntityToDto(genreEntity);
    }

    public GenreDto getGenres(Long id) throws Exception {
        GenreEntity genreEntity = genreRepository.findById(id)
                .orElseThrow(() -> new Exception("Genres not found about that name"));
        return GenreDto.mapEntityToDto(genreEntity);
    }

    public GenreDto updateGenres(Long id, GenreDto dto) throws Exception {
        GenreEntity genreEntity = genreRepository.findById(id)
                .orElseThrow(() -> new Exception("Genres not found about that name"));
        if (dto.getGenres() != null) {
            genreEntity.setGenres(dto.getGenres());
        }
        genreEntity = genreRepository.save(genreEntity);
        return GenreDto.mapEntityToDto(genreEntity);
    }

    public void deleteGenres(Long id) {
        genreRepository.deleteById(id);
    }

    //for CSV
    public void save(MultipartFile file) {
        try {
            List<GenreEntity> entities = GenreHelper.csvToGenreEntity(file.getInputStream());
            genreRepository.saveAll(entities);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public ByteArrayInputStream load() {
        List<GenreEntity> entities = genreRepository.findAll();
        ByteArrayInputStream in = GenreHelper.genreEntityToCSV(entities);
        return in;
    }

    public List<GenreEntity> getAllGenres() {
        return genreRepository.findAll();
    }
}
