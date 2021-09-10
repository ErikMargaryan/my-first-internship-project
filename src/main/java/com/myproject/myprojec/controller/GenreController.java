package com.myproject.myprojec.controller;

import com.myproject.myprojec.controller.dto.GenreDto;
import com.myproject.myprojec.persistence.entity.GenreEntity;
import com.myproject.myprojec.service.GenreService;
import com.myproject.myprojec.service.criteria.SearchCriteria;
import com.myproject.myprojec.service.model.QueryResponseWrapper;
import com.myproject.myprojec.service.validation.Create;
import com.myproject.myprojec.service.validation.Update;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("genres")
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/{id}")
    public GenreDto getGenre(@PathVariable("id") Long id) throws Exception {
        GenreEntity genreEntity = genreService.getById(id);
        return GenreDto.mapEntityToDto(genreEntity);
    }

    @GetMapping
    public List<GenreDto> getGenres(SearchCriteria searchCriteria) {
        QueryResponseWrapper<GenreEntity> entityQueryResponseWrapper = genreService.getGenres(searchCriteria);
        List<GenreDto> dtos = entityQueryResponseWrapper.getData().stream().map(GenreDto::mapEntityToDto).collect(Collectors.toList());
        return dtos;
    }

    @GetMapping("json-format")
    public ResponseEntity<List<GenreDto>> getAllGenres() {
        try {
            List<GenreDto> dtos = genreService.getAllGenres().stream().map(GenreDto::mapEntityToDto).collect(Collectors.toList());
            if (dtos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<GenreDto> addGenre(@RequestBody @Validated(Create.class) GenreEntity entity) throws Exception {
        if (entity.getGenres() == null) {
            throw new Exception("Genre is required");
        }
        GenreDto genreDto = GenreDto.mapEntityToDto(genreService.createGenres(entity));
        return ResponseEntity.status(HttpStatus.CREATED).body(genreDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreDto> updateGenre(@PathVariable("id") Long id,
                                                @Validated(Update.class)
                                                @RequestBody GenreEntity entity) throws Exception {
        GenreDto genreDto = GenreDto.mapEntityToDto(genreService.updateGenres(id, entity));
        if (genreDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(genreDto);
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable("id") Long id) {
        genreService.deleteGenres(id);
    }

    @PostMapping("/upload-genre-csv")
    public void uploadGenre(@RequestParam(name = "file") MultipartFile file) throws NotFoundException {
        genreService.parseCsv(file);
    }

}
