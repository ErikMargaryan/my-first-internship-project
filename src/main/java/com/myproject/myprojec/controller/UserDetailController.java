package com.myproject.myprojec.controller;

//import com.myproject.myprojec.csvUpload.ResponseMessage;
//import com.myproject.myprojec.csvUpload.csvHelper.UserDetailHelper;

import com.myproject.myprojec.service.UserDetailService;
import com.myproject.myprojec.service.criteria.SearchCriteria;
import com.myproject.myprojec.service.dto.UserDetailDto;
import com.myproject.myprojec.service.model.QueryResponseWrapper;
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

    @GetMapping
    public QueryResponseWrapper<UserDetailDto> getUserDetails(SearchCriteria searchCriteria) {
        return userDetailService.getUserDetails(searchCriteria);
    }

//    @GetMapping("json-format")
//    public ResponseEntity<List<UserDetailEntity>>  getAllAuthors() {
//        try {
//            List<UserDetailEntity> entities = userDetailService.getAllUserDetails();
//            if (entities.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(entities, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @GetMapping("/download/{fileName:.+}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
//        InputStreamResource file = new InputStreamResource(userDetailService.load());
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
//                .contentType(MediaType.parseMediaType("application/csv"))
//                .body(file);
//    }

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

//    //upload CSV
//    @PostMapping("/upload")
//    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
//        String message = "";
//        if (UserDetailHelper.hasCSVFormat(file)) {
//            try {
//                userDetailService.save(file);
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
//        message = "Please upload a csv file!";
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message,""));
//    }

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


//    @PostMapping("/upload-csv-file")
//    public ResponseEntity<UserDetailDto> uploadCSVFile(@RequestParam("file") MultipartFile file) throws IOException {
//
//            // parse CSV file to create a list of `User` objects
//            try(Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
//
//                // create csv bean reader
//                CsvToBean<UserDetail> csvToBean = new CsvToBeanBuilder(reader)
//                        .withType(UserDetail.class)
//                        .withIgnoreLeadingWhiteSpace(true)
//                        .build();
//
//                // convert `CsvToBean` object to list of userDetails
//                List<UserDetail> userDetails = csvToBean.parse();
//                // TODO: save users in DB?
//                List<UserDetailDto> list = new ArrayList<>();
//                for (UserDetail userDetail : userDetails) {
//                    UserDetailDto dto = UserDetail.mapCsvToDto(userDetail);
//                    list.add(dto);
//                }
//                UserDetailDto userDetailDto = userDetailService.createUserDetail(list);
//                    return ResponseEntity.status(HttpStatus.CREATED).body(userDetailDto);
//            } catch (IOException e) {
//                System.out.println(e);
//        }
//            //??
//            UserDetailDto dto = new UserDetailDto();
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
//    }


}
