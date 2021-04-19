package com.myproject.myprojec.controller;

import com.myproject.myprojec.service.GenreService;
import com.myproject.myprojec.csvUpload.criteria.SearchCriteria;
import com.myproject.myprojec.service.dto.GenreDto;
import com.myproject.myprojec.service.model.QueryResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return genreService.getGenres(id);
    }

    @GetMapping
    public QueryResponseWrapper<GenreDto> getGenres(SearchCriteria searchCriteria) {
        return genreService.getGenres(searchCriteria);
    }

//    @GetMapping("json-format")
//    public ResponseEntity<List<GenreEntity>> getAllGenres() {
//        try {
//            List<GenreEntity> entities = genreService.getAllGenres();
//            if (entities.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(entities, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PostMapping()
    public ResponseEntity<GenreDto> createGenre(@RequestBody GenreDto dto) throws Exception {
        if (dto.getGenres() == null) {
            throw new Exception("Genre is required");
        }
        GenreDto genre = genreService.createGenres(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(genre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreDto> updateGenre(@PathVariable("id") Long id,
                                                @RequestBody GenreDto dto) throws Exception {
        GenreDto genre = genreService.updateGenres(id, dto);
        if (genre == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(genre);
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable("id") Long id) {
        genreService.deleteGenres(id);
    }
}
