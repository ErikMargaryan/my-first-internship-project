package com.myproject.myprojec.controller;

import com.myproject.myprojec.persistence.entity.AuthorEntity;
import com.myproject.myprojec.service.AuthorService;
import com.myproject.myprojec.csvUpload.criteria.SearchCriteria;
import com.myproject.myprojec.service.dto.AuthorDto;
import com.myproject.myprojec.service.model.QueryResponseWrapper;
import com.myproject.myprojec.service.validation.Create;
import com.myproject.myprojec.service.validation.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        return authorService.getAuthor(id);
    }

    @GetMapping
    public QueryResponseWrapper<AuthorDto> getAuthors(SearchCriteria searchCriteria) {
        return authorService.getAuthors(searchCriteria);
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

    @PostMapping()
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<AuthorDto> addAuthor(@RequestBody @Validated(Create.class) AuthorDto dto) throws Exception {
        if (dto.getName() == null) {
            throw  new Exception("Author's name is required");
        }
        AuthorDto author = authorService.createAuthor(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(author);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable("id") Long id,
                                                  @Validated(Update.class)
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
