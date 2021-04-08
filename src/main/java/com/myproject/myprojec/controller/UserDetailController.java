package com.myproject.myprojec.controller;

import com.myproject.myprojec.csvUpload.ResponseMessage;
import com.myproject.myprojec.csvUpload.csvHelper.UserDetailHelper;
import com.myproject.myprojec.dto.UserDetailDto;
import com.myproject.myprojec.persistence.entity.UserDetailEntity;
import com.myproject.myprojec.service.UserDetailService;
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

    @GetMapping("json-format")
    public ResponseEntity<List<UserDetailEntity>>  getAllAuthors() {
        try {
            List<UserDetailEntity> entities = userDetailService.getAllUserDetails();
            if (entities.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(entities, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        InputStreamResource file = new InputStreamResource(userDetailService.load());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
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

    //upload CSV
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (UserDetailHelper.hasCSVFormat(file)) {
            try {
                userDetailService.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/api/csv/download/")
                        .path(file.getOriginalFilename())
                        .toUriString();

                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message,fileDownloadUri));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message,""));
            }
        }
        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message,""));
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
