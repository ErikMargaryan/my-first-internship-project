package com.myproject.myprojec.controller;

import com.myproject.myprojec.csvUpload.criteria.SearchCriteria;
import com.myproject.myprojec.persistence.entity.UserRatedBookEntity;
import com.myproject.myprojec.service.UserRatedBookService;
import com.myproject.myprojec.service.dto.UserRatedBookDto;
import com.myproject.myprojec.service.model.QueryResponseWrapper;
import com.myproject.myprojec.service.validation.Create;
import com.myproject.myprojec.service.validation.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        return userRatedBookService.getUsersRatedBooks(id);
    }

    @GetMapping
    public QueryResponseWrapper<UserRatedBookDto> getUserRates(SearchCriteria searchCriteria) {
        return userRatedBookService.getUserRates(searchCriteria);
    }

    @GetMapping("json-format")
    public ResponseEntity<List<UserRatedBookEntity>> getAllAuthors() {
        try {
            List<UserRatedBookEntity> entities = userRatedBookService.getAllUserRates();
            if (entities.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(entities, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<UserRatedBookDto> addRate(@RequestBody @Validated(Create.class) UserRatedBookDto dto) throws Exception {
        UserRatedBookDto usersRatedBooks = userRatedBookService.createUsersRatedBooks(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usersRatedBooks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRatedBookDto> updateBooksRates(@PathVariable("id") Long id,
                                                             @Validated(Update.class)
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
