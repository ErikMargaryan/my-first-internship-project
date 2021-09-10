package com.myproject.myprojec.service;

import com.myproject.myprojec.csvUpload.control.CsvControl;
import com.myproject.myprojec.csvUpload.csvModel.Genre;
import com.myproject.myprojec.persistence.entity.GenreEntity;
import com.myproject.myprojec.persistence.repository.GenreRepository;
import com.myproject.myprojec.service.criteria.SearchCriteria;
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

    public GenreEntity createGenres(GenreEntity genreEntity) {
        genreEntity = genreRepository.save(genreEntity);
        return genreEntity;
    }

    public GenreEntity getById(Long id) throws Exception {
        return genreRepository.findById(id)
                .orElseThrow(() -> new Exception("Genres not found about that name"));
    }

    public QueryResponseWrapper<GenreEntity> getGenres(SearchCriteria searchCriteria) {
        Page<GenreEntity> page = genreRepository.findALLWithPagination(searchCriteria.composePageRequest());
        List<GenreEntity> content = page.getContent();
        return new QueryResponseWrapper<>(page.getTotalElements(), content);
    }

    public GenreEntity updateGenres(Long id, GenreEntity entity) throws Exception {
        GenreEntity genreEntity = genreRepository.findById(id)
                .orElseThrow(() -> new Exception("Genres not found about that name"));
        if (entity.getGenres() != null) {
            genreEntity.setGenres(entity.getGenres());
        }
        genreEntity = genreRepository.save(genreEntity);
        return entity;
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
