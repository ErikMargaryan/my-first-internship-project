package com.myproject.myprojec.controller;

import com.myproject.myprojec.csvUpload.ResponseMessage;
import com.myproject.myprojec.csvUpload.csvHelper.GenreHelper;
import com.myproject.myprojec.dto.GenreDto;
import com.myproject.myprojec.persistence.entity.GenreEntity;
import com.myproject.myprojec.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

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
        GenreDto dto = genreService.getGenres(id);
        return dto;
    }

    @GetMapping("json-format")
    public ResponseEntity<List<GenreEntity>> getAllGenres() {
        try {
            List<GenreEntity> entities = genreService.getAllGenres();
            if (entities.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(entities, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<GenreDto> createGenre(@RequestBody GenreDto dto) throws Exception {
        if (dto.getGenres() == null) {
            throw new Exception("Genre is required");
        }
        GenreDto genre = genreService.createGenres(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(genre);
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        InputStreamResource file = new InputStreamResource(genreService.load());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

    //upload CSV
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (GenreHelper.hasCSVFormat(file)) {
            try {
                genreService.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/api/csv/download/")
                        .path(file.getOriginalFilename())
                        .toUriString();

                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message, fileDownloadUri));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message, ""));
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message, ""));

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
