package com.myproject.myprojec.controller;

import com.myproject.myprojec.service.criteria.SearchCriteria;
import com.myproject.myprojec.persistence.entity.UserRatedBookEntity;
import com.myproject.myprojec.service.UserRatedBookService;
import com.myproject.myprojec.controller.dto.UserRatedBookDto;
import com.myproject.myprojec.service.model.QueryResponseWrapper;
import com.myproject.myprojec.service.validation.Create;
import com.myproject.myprojec.service.validation.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
        UserRatedBookEntity userRatedBookEntity = userRatedBookService.getById(id);
        return UserRatedBookDto.mapEntityToDto(userRatedBookEntity);
    }

    @GetMapping
    public List<UserRatedBookDto> getUserRates(SearchCriteria searchCriteria) {
        QueryResponseWrapper<UserRatedBookEntity> entityQueryResponseWrapper = userRatedBookService.getUserRates(searchCriteria);
        List<UserRatedBookDto> dtos = entityQueryResponseWrapper.getData().stream().map(UserRatedBookDto::mapEntityToDto).collect(Collectors.toList());
        return dtos;
    }

    @GetMapping("json-format")
    public ResponseEntity<List<UserRatedBookDto>> getAllAuthors() {
        try {
            List<UserRatedBookDto> dtos = userRatedBookService.getAllUserRates().stream().map(UserRatedBookDto::mapEntityToDto).collect(Collectors.toList());
            if (dtos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<UserRatedBookDto> addRate(@RequestBody @Validated(Create.class) UserRatedBookEntity entity) throws Exception {
        UserRatedBookDto usersRatedBooksDto = UserRatedBookDto.mapEntityToDto(userRatedBookService.createUsersRatedBooks(entity));
        return ResponseEntity.status(HttpStatus.CREATED).body(usersRatedBooksDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRatedBookDto> updateBooksRates(@PathVariable("id") Long id,
                                                             @Validated(Update.class)
                                                             @RequestBody UserRatedBookEntity entity) throws Exception {
        UserRatedBookDto usersRatedBookDto = UserRatedBookDto.mapEntityToDto(userRatedBookService.updateUsersRate(id, entity));
        if (usersRatedBookDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usersRatedBookDto);
    }

    @DeleteMapping("/{id}")
    public void deleteRate(@PathVariable("id") Long id) {
        userRatedBookService.deleteUsersRate(id);
    }
}
