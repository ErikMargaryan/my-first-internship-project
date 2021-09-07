package com.myproject.myprojec.controller;

import com.myproject.myprojec.persistence.entity.BookEntity;
import com.myproject.myprojec.service.BookService;
import com.myproject.myprojec.service.criteria.SearchCriteria;
import com.myproject.myprojec.service.dto.BookDto;
import com.myproject.myprojec.service.model.QueryResponseWrapper;
import com.myproject.myprojec.service.validation.Create;
import com.myproject.myprojec.service.validation.Update;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public ResponseEntity<BookDto> addBook(@RequestBody @Validated(Create.class) BookDto dto) throws Exception {
        if (dto.getTitle() == null) {
            throw new Exception("Title is required");
        }
        if (dto.getIsbn() == null) {
            throw new Exception("ISBN is required");
        }
        if (dto.getPublisher() == null) {
            throw new Exception("Publisher is required");
        }

        BookDto book = bookService.createBook(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @GetMapping("/{id}")
    public BookDto getBook(@PathVariable("id") Long id) throws Exception {
        return bookService.getBook(id);
    }

    @GetMapping
    public QueryResponseWrapper<BookDto> getBooks(SearchCriteria searchCriteria) {
        return bookService.getBooks(searchCriteria);
    }

    @GetMapping("json-format")
    public ResponseEntity<List<BookEntity>> getAllBooks() {
        try {
            List<BookEntity> entities = bookService.getAllBooks();
            if (entities.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(entities, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public ResponseEntity<BookDto> updateBook(@PathVariable("id") Long id,
                                              @Validated(Update.class)
                                              @RequestBody BookDto dto) throws Exception {
        BookDto book = bookService.updateBookData(id, dto);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public void deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
    }

    @PostMapping("/upload-book-csv")
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public void uploadBook(@RequestParam(name = "file") MultipartFile file) throws NotFoundException {
        bookService.parseCsv(file);
    }

}
