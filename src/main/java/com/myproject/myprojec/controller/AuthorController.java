package com.myproject.myprojec.controller;

import com.myproject.myprojec.persistence.entity.AuthorEntity;
import com.myproject.myprojec.service.AuthorService;
import com.myproject.myprojec.service.criteria.SearchCriteria;
import com.myproject.myprojec.controller.dto.AuthorDto;
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
import java.util.stream.Collectors;

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
        AuthorEntity authorEntity = authorService.getById(id);
        return AuthorDto.mapEntityToDto(authorEntity);
    }

    @GetMapping
    public List<AuthorDto> getAuthors(SearchCriteria searchCriteria) {
        QueryResponseWrapper<AuthorEntity> entityQueryResponseWrapper = authorService.getAuthors(searchCriteria);
        List<AuthorDto> dtos = entityQueryResponseWrapper.getData().stream().map(AuthorDto::mapEntityToDto).collect(Collectors.toList());
        return dtos;
    }

    @GetMapping("json-format")
    public ResponseEntity<List<AuthorDto>> getAllAuthors() {
        try {
            List<AuthorDto> dtos = authorService.getAllAuthors().stream().map(AuthorDto::mapEntityToDto).collect(Collectors.toList());
            if (dtos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<AuthorDto> addAuthor(@RequestBody @Validated(Create.class) AuthorEntity authorEntity) throws Exception {
        if (authorEntity.getName() == null) {
            throw  new Exception("Author's name is required");
        }
        AuthorDto authorDto = AuthorDto.mapEntityToDto(authorService.createAuthor(authorEntity));
        return ResponseEntity.status(HttpStatus.CREATED).body(authorDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable("id") Long id,
                                                  @Validated(Update.class)
                                                  @RequestBody AuthorEntity entity) throws Exception {
        AuthorDto authorDto = AuthorDto.mapEntityToDto(authorService.updateAuthorData(id, entity));
        if (authorDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(authorDto);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable("id") Long id) {
        authorService.delete(id);
    }
}
