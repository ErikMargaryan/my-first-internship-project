package com.myproject.myprojec.controller;

import com.myproject.myprojec.dto.UserDetailDto;
import com.myproject.myprojec.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user-detail")
public class UserDetailController {

    private final UserDetailService userDetailService;

    @Autowired
    public UserDetailController(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @GetMapping("/{id}")
    public UserDetailDto getUserDetail(@PathVariable("id") Long id) throws Exception {
        UserDetailDto user = userDetailService.getUserDetail(id);
        return user;
    }

    @PostMapping()
    public ResponseEntity<UserDetailDto> addUserDetail(@RequestBody UserDetailDto dto) throws Exception {
        if (dto.getAddress() == null) {
            throw new Exception("Address is required");
        }
        if (dto.getPhoneNumber() == null) {
            throw new Exception("Phone number is required");
        }
        UserDetailDto userDetail = userDetailService.createUserDetail(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDetail);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDetailDto> updateUserDetail(@PathVariable("id") Long id,
                                                          @RequestBody UserDetailDto dto) throws Exception {
        UserDetailDto userDetail = userDetailService.updateUserDetail(id, dto);
        if (userDetail == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDetail);
    }

    @DeleteMapping("/{id}")
    public void deleteUserDetail(@PathVariable("id") Long id) {
        userDetailService.deleteUserDetail(id);
    }
}
