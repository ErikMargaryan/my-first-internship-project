package com.myproject.myprojec.controller;

import com.myproject.myprojec.dto.BookDto;
//import com.myproject.myprojec.model.BookWrapper;
import com.myproject.myprojec.model.QueryResponseWrapper;
import com.myproject.myprojec.service.BookService;
import com.myproject.myprojec.service.criteria.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public BookDto getBook(@PathVariable("id") Long id) throws Exception {
        BookDto book = bookService.getBook(id);
        return book;
    }

//    @GetMapping
//    public QueryResponseWrapper<BookDto> getBooks(SearchCriteria searchCriteria) {
//        return bookService.getBooks(searchCriteria);
//    }

    //read validate annotation
    @PostMapping()
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto dto) throws Exception {
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

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable("id") Long id,
                                              @RequestBody BookDto dto) throws Exception {
        BookDto book = bookService.updateBookData(id, dto);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
    }

}
