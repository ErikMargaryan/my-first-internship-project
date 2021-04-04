package com.myproject.myprojec.controller;

import com.myproject.myprojec.dto.UserDto;
//import com.myproject.myprojec.model.UserWrapper;
import com.myproject.myprojec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable("id") Long id) throws Exception {
        UserDto user = userService.getUser(id);
        return user;
    }

//    @GetMapping("/with-pagination")
//    public QueryResponseWrapper<UserWrapper> getBooks(SearchCriteria searchCriteria) {
//        return userService.getUsers(searchCriteria);
//    }

    @PostMapping()
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto dto) throws Exception {
        if (dto.getFirstName() == null) {
            throw new Exception("FirstName is required");
        }
        if (dto.getLastName() == null) {
            throw new Exception("LastName is required");
        }
        if (dto.getEmail() == null) {
            throw new Exception("Email is required");
        }
        if (dto.getUsername() == null) {
            throw new Exception("Username is required");
        }
        if (dto.getPassword() == null) {
            throw new Exception("Password is required");
        }
        UserDto user = userService.creatUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id,
                                              @RequestBody UserDto dto) throws Exception {
        UserDto user = userService.updateUser(id, dto);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }
}
