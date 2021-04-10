package com.myproject.myprojec.controller;

//import com.myproject.myprojec.csvUpload.ResponseMessage;
//import com.myproject.myprojec.csvUpload.csvHelper.AuthorHelper;
//import com.myproject.myprojec.csvUpload.csvHelper.UserRatedBookHelper;
import com.myproject.myprojec.dto.UserRatedBookDto;
import com.myproject.myprojec.model.QueryResponseWrapper;
import com.myproject.myprojec.persistence.entity.AuthorEntity;
import com.myproject.myprojec.persistence.entity.UserRatedBookEntity;
import com.myproject.myprojec.service.UserRatedBookService;
import com.myproject.myprojec.service.criteria.SearchCriteria;
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
@RequestMapping("user-rated-book")
public class UserRatedBookController {

    private final UserRatedBookService userRatedBookService;

    @Autowired
    public UserRatedBookController(UserRatedBookService userRatedBookService) {
        this.userRatedBookService = userRatedBookService;
    }

    @GetMapping("/{id}")
    public UserRatedBookDto getRate(@PathVariable("id") Long id) throws Exception {
        UserRatedBookDto userRated = userRatedBookService.getUsersRatedBooks(id);
        return userRated;
    }

    @GetMapping
    public QueryResponseWrapper<UserRatedBookDto> getUserRates(SearchCriteria searchCriteria) {
        return userRatedBookService.getUserRates(searchCriteria);
    }

//    @GetMapping("json-format")
//    public ResponseEntity<List<UserRatedBookEntity>> getAllAuthors() {
//        try {
//            List<UserRatedBookEntity> entities = userRatedBookService.getAllUserRates();
//            if (entities.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(entities, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/download/{fileName:.+}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
//        InputStreamResource file = new InputStreamResource(userRatedBookService.load());
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
//                .contentType(MediaType.parseMediaType("application/csv"))
//                .body(file);
//    }

    @PostMapping()
    public ResponseEntity<UserRatedBookDto> addRate(@RequestBody UserRatedBookDto dto) throws Exception {
        UserRatedBookDto usersRatedBooks = userRatedBookService.createUsersRatedBooks(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usersRatedBooks);
    }

//    //upload CSV
//    @PostMapping("/upload")
//    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
//        String message = "";
//
//        if (UserRatedBookHelper.hasCSVFormat(file)) {
//            try {
//                userRatedBookService.save(file);
//                message = "Uploaded the file successfully: " + file.getOriginalFilename();
//                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                        .path("/api/csv/download/")
//                        .path(file.getOriginalFilename())
//                        .toUriString();
//
//                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message,fileDownloadUri));
//            } catch (Exception e) {
//                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message,""));
//            }
//        }
//
//        message = "Please upload a csv file!";
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message,""));
//    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRatedBookDto> updateBooksRates(@PathVariable("id") Long id,
                                                             @RequestBody UserRatedBookDto dto) throws Exception {
        UserRatedBookDto usersRated = userRatedBookService.updateUsersRate(id, dto);
        if (usersRated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usersRated);
    }

    @DeleteMapping("/{id}")
    public void deleteRate(@PathVariable("id") Long id) {
        userRatedBookService.deleteUsersRate(id);
    }
}
