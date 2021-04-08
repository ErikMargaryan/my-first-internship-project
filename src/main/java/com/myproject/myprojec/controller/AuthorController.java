package com.myproject.myprojec.controller;

import com.myproject.myprojec.csvUpload.ResponseMessage;
import com.myproject.myprojec.csvUpload.csvHelper.AuthorHelper;
import com.myproject.myprojec.dto.AuthorDto;
import com.myproject.myprojec.persistence.entity.AuthorEntity;
import com.myproject.myprojec.service.AuthorService;
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
@RequestMapping("authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/{id}")
    public AuthorDto getAuthor(@PathVariable("id") Long id) throws Exception {
        AuthorDto author = authorService.getAuthor(id);
        return author;
    }

    @GetMapping("json-format")
    public ResponseEntity<List<AuthorEntity>> getAllAuthors() {
        try {
            List<AuthorEntity> entities = authorService.getAllAuthors();
            if (entities.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(entities, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        InputStreamResource file = new InputStreamResource(authorService.load());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

    @PostMapping()
    public ResponseEntity<AuthorDto> addAuthor(@RequestBody AuthorDto dto) throws Exception {
        if (dto.getName() == null) {
            throw  new Exception("Author's name is required");
        }
        AuthorDto author = authorService.createAuthor(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(author);
    }

    //upload CSV
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (AuthorHelper.hasCSVFormat(file)) {
            try {
                authorService.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/api/csv/download/")
                        .path(file.getOriginalFilename())
                        .toUriString();

                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message,fileDownloadUri));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message,""));
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message,""));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable("id") Long id,
                                                  @RequestBody AuthorDto dto) throws Exception {
        AuthorDto author = authorService.updateAuthorData(id, dto);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(author);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable("id") Long id) {
        authorService.delete(id);
    }
}
