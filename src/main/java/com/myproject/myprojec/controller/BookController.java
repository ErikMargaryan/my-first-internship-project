package com.myproject.myprojec.controller;

import com.myproject.myprojec.service.BookService;
import com.myproject.myprojec.service.criteria.SearchCriteria;
import com.myproject.myprojec.service.dto.BookDto;
import com.myproject.myprojec.service.model.QueryResponseWrapper;
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

    @GetMapping
    public QueryResponseWrapper<BookDto> getBooks(SearchCriteria searchCriteria) {
        return bookService.getBooks(searchCriteria);
    }

//    @GetMapping("json-format")
//    public ResponseEntity<List<BookEntity>> getAllBooks() {
//        try {
//            List<BookEntity> entities = bookService.getAllBooks();
//            if (entities.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(entities, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @GetMapping("/download/{fileName:.+}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
//        InputStreamResource file = new InputStreamResource(bookService.load());
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
//                .contentType(MediaType.parseMediaType("application/csv"))
//                .body(file);
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

    //upload CSV
//    @PostMapping("/upload")
//    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
//        String message = "";
//
//        if (BookHelper.hasCSVFormat(file)) {
//            try {
//                bookService.save(file);
//                message = "Uploaded the file successfully: " + file.getOriginalFilename();
//                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                        .path("/api/csv/download/")
//                        .path(Objects.requireNonNull(file.getOriginalFilename()))
//                        .toUriString();
//
//                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message, fileDownloadUri));
//            } catch (Exception e) {
//                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message, ""));
//            }
//        }
//
//        message = "Please upload a csv file!";
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message, ""));
//
//    }

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
