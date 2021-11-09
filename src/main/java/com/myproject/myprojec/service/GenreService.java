package com.myproject.myprojec.service;

import com.myproject.myprojec.csvUpload.control.CsvControl;
import com.myproject.myprojec.service.criteria.SearchCriteria;
import com.myproject.myprojec.csvUpload.csvModel.Genre;
import com.myproject.myprojec.persistence.entity.GenreEntity;
import com.myproject.myprojec.persistence.repository.GenreRepository;
import com.myproject.myprojec.service.dto.GenreDto;
import com.myproject.myprojec.service.model.QueryResponseWrapper;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {

    private final GenreRepository genreRepository;
    private final CsvControl<Genre> csvControl;

    @Autowired
    public GenreService(GenreRepository genreRepository, CsvControl<Genre> csvControl) {
        this.genreRepository = genreRepository;
        this.csvControl = csvControl;
    }

    public GenreDto createGenres(GenreDto dto) {
        GenreEntity genreEntity = new GenreEntity();
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
            genreEntity.setGenre(dto.getGenres());
        }
        genreEntity = genreRepository.save(genreEntity);
        return GenreDto.mapEntityToDto(genreEntity);
    }

    public void deleteGenres(Long id) {
        genreRepository.deleteById(id);
    }

    public List<GenreEntity> getAllGenres() {
        return genreRepository.findAll();
    }

    public void parseCsv(MultipartFile file) throws NotFoundException {
        List<List<Genre>> genres = csvControl.getEntitiesFromCsv(file, Genre.class);
        List<List<GenreEntity>> genresList = genres.stream().map(genre -> genre.stream()
                .map(Genre::mapCsvToEntity)
                .collect(Collectors.toList()))
                .collect(Collectors.toList());
        genresList.forEach(genreRepository::saveAll);
    }

}
