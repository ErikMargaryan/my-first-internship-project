package com.myproject.myprojec.controller;

import com.myproject.myprojec.controller.dto.BookDto;
import com.myproject.myprojec.persistence.entity.BookEntity;
import com.myproject.myprojec.service.BookService;
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
@RequestMapping("books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping()
    public ResponseEntity<BookDto> addBook(@RequestBody @Validated(Create.class) BookEntity entity) throws Exception {
        if (entity.getTitle() == null) {
            throw new Exception("Title is required");
        }
        if (entity.getIsbn() == null) {
            throw new Exception("ISBN is required");
        }
        if (entity.getPublisher() == null) {
            throw new Exception("Publisher is required");
        }

        BookDto bookDto = BookDto.mapEntityToDto(bookService.createBook(entity));
        return ResponseEntity.status(HttpStatus.CREATED).body(bookDto);
    }

    @GetMapping("/{id}")
    public BookDto getBook(@PathVariable("id") Long id) throws Exception {
        BookEntity bookEntity = bookService.getById(id);
        return BookDto.mapEntityToDto(bookEntity);
    }

    @GetMapping
    public List<BookDto> getBooks(SearchCriteria searchCriteria) {
        QueryResponseWrapper<BookEntity> entityQueryResponseWrapper = bookService.getBooks(searchCriteria);
        List<BookDto> dtos = entityQueryResponseWrapper.getData().stream().map(BookDto::mapEntityToDto).collect(Collectors.toList());
        return dtos;
    }

    @GetMapping("json-format")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        try {
            List<BookDto> dtos = bookService.getAllBooks().stream().map(BookDto::mapEntityToDto).collect(Collectors.toList());
            if (dtos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable("id") Long id,
                                              @Validated(Update.class)
                                              @RequestBody BookEntity entity) throws Exception {
        BookDto bookDto = BookDto.mapEntityToDto(bookService.updateBookData(id, entity));
        if (bookDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookDto);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
    }

    @PostMapping("/upload-book-csv")
    public void uploadBook(@RequestParam(name = "file") MultipartFile file) throws NotFoundException {
        bookService.parseCsv(file);
    }

}
