//package com.myproject.myprojec.controller;
//
//import com.myproject.myprojec.service.BookService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.Map;
//import java.util.Objects;
//
//@RestController
//public class CsvController {
//
//    private final BookService bookService;
//
//    @Autowired
//    public CsvController(BookService bookService) {
//        this.bookService = bookService;
//    }
//
//    @PostMapping("/import-from-csv-file")
//    public ResponseEntity<?> uploadCSVFile(@RequestParam(name = "file") MultipartFile csvFile) {
//        if (csvFile.isEmpty()) {
//            return ResponseEntity.badRequest().body(Map.of("message", "Requires request part 'file' is ..."));
//        }
//        if (!Objects.equals(csvFile.getContentType(), "text/csv")) {
//            return ResponseEntity.badRequest().body(Map.of("message", "The file must be in csv format"));
//        }
//        Map<String, Integer> result = bookService.parseCsv(csvFile);
//        return ResponseEntity.ok().body(result);
//    }
//}
