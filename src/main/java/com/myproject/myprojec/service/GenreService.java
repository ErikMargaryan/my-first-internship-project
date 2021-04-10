package com.myproject.myprojec.service;

//import com.myproject.myprojec.csvUpload.csvHelper.GenreHelper;
import com.myproject.myprojec.dto.GenreDto;
import com.myproject.myprojec.model.QueryResponseWrapper;
import com.myproject.myprojec.persistence.entity.GenreEntity;
import com.myproject.myprojec.persistence.rpository.GenreRepository;
import com.myproject.myprojec.service.criteria.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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

    public QueryResponseWrapper<GenreDto> getGenres(SearchCriteria searchCriteria) {
        Page<GenreEntity> page = genreRepository.findALLWithPagination(searchCriteria.composePageRequest());
        List<GenreEntity> content = page.getContent();
        List<GenreDto> dtos = content.stream().map(GenreDto::mapEntityToDto).collect(Collectors.toList());
        return new QueryResponseWrapper<>(page.getTotalElements(), dtos);
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

//    //for CSV
//    public void save(MultipartFile file) {
//        try {
//            List<GenreEntity> entities = GenreHelper.csvToGenreEntity(file.getInputStream());
//            genreRepository.saveAll(entities);
//        } catch (IOException e) {
//            throw new RuntimeException("fail to store csv data: " + e.getMessage());
//        }
//    }
//
//    public ByteArrayInputStream load() {
//        List<GenreEntity> entities = genreRepository.findAll();
//        ByteArrayInputStream in = GenreHelper.genreEntityToCSV(entities);
//        return in;
//    }
//
//    public List<GenreEntity> getAllGenres() {
//        return genreRepository.findAll();
//    }
}
