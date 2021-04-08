package com.myproject.myprojec.controller;

import com.myproject.myprojec.dto.AuthorDto;
import com.myproject.myprojec.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    public ResponseEntity<AuthorDto> addAuthor(@RequestBody AuthorDto dto) throws Exception {
        if (dto.getName() == null) {
            throw  new Exception("Author's name is required");
        }
        AuthorDto author = authorService.createAuthor(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(author);
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
