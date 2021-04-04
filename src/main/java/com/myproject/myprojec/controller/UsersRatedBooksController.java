package com.myproject.myprojec.controller;

import com.myproject.myprojec.dto.UserRatedBookDto;
import com.myproject.myprojec.service.UserRatedBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersRatedBooksController {

    private final UserRatedBookService userRatedBookService;

    @Autowired
    public UsersRatedBooksController(UserRatedBookService userRatedBookService) {
        this.userRatedBookService = userRatedBookService;
    }

//    @GetMapping("/{Id}")
//    public UsersRatedBooksDto getRate(@PathVariable("id") Long id, @PathVariable String Id) throws Exception {
//        UsersRatedBooksDto usersRated = usersRatedBooksService.getUsersRatedBooks(id);
//        return usersRated;
//    }

    @PostMapping()
    public ResponseEntity<UserRatedBookDto> addRate(@RequestBody UserRatedBookDto dto) throws Exception {
        UserRatedBookDto usersRatedBooks = userRatedBookService.createUsersRatedBooks(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usersRatedBooks);
    }

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
