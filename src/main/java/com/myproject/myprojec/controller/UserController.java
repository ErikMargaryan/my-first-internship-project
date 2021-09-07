package com.myproject.myprojec.controller;

import com.myproject.myprojec.service.criteria.SearchCriteria;
import com.myproject.myprojec.service.UserService;
import com.myproject.myprojec.service.dto.UserDto;
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

import static com.myproject.myprojec.config.session.SessionUser.SESSION_USER_KEY;

@Validated
@RestController
@RequestMapping("users")
@SessionAttributes(SESSION_USER_KEY)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable("id") Long id) throws Exception {
        return userService.getUser(id);
    }

    @GetMapping
    public QueryResponseWrapper<UserDto> getBooks(SearchCriteria searchCriteria) {
        return userService.getUsers(searchCriteria);
    }

    @PostMapping("/registration")
    public ResponseEntity<UserDto> addUser(@RequestBody @Validated(Create.class) UserDto dto) throws Exception {
        if (dto.getFirstName() == null) {
            throw new Exception("FirstName is required");
        }
        if (dto.getLastName() == null) {
            throw new Exception("LastName is required");
        }
        if (dto.getAddress() == null) {
            throw new Exception("Address is required");
        }
        if (dto.getPhoneNumber() == null) {
            throw new Exception("Phone number is required");
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
                                              @Validated(Update.class)
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

    @PostMapping("/upload-user-csv")
    public void uploadUser(@RequestParam(name = "file") MultipartFile file) throws NotFoundException {
        userService.parseCsv(file);
    }

}
