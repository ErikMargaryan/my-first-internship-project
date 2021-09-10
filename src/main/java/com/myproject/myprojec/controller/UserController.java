package com.myproject.myprojec.controller;

import com.myproject.myprojec.controller.dto.UserDto;
import com.myproject.myprojec.persistence.entity.UserEntity;
import com.myproject.myprojec.service.UserService;
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
        UserEntity userEntity = userService.getById(id);
        return UserDto.mapEntityToDto(userEntity);
    }

    @GetMapping
    public List<UserDto> getUsers(SearchCriteria searchCriteria) {
        QueryResponseWrapper<UserEntity> entityQueryResponseWrapper = userService.getUsers(searchCriteria);
        List<UserDto> dtos = entityQueryResponseWrapper.getData().stream().map(UserDto::mapEntityToDto).collect(Collectors.toList());
        return dtos;
    }

    @PostMapping("/registration")
    public ResponseEntity<UserDto> addUser(@RequestBody @Validated(Create.class) UserEntity entity) throws Exception {
        if (entity.getFirstName() == null) {
            throw new Exception("FirstName is required");
        }
        if (entity.getLastName() == null) {
            throw new Exception("LastName is required");
        }
        if (entity.getAddress() == null) {
            throw new Exception("Address is required");
        }
        if (entity.getPhoneNumber() == null) {
            throw new Exception("Phone number is required");
        }
        if (entity.getEmail() == null) {
            throw new Exception("Email is required");
        }
        if (entity.getUsername() == null) {
            throw new Exception("Username is required");
        }
        if (entity.getPassword() == null) {
            throw new Exception("Password is required");
        }
        UserDto userDto = UserDto.mapEntityToDto(userService.creatUser(entity));
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id,
                                              @Validated(Update.class)
                                              @RequestBody UserEntity entity) throws Exception {
        UserDto userDto = UserDto.mapEntityToDto(userService.updateUser(id, entity));
        if (userDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDto);
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
