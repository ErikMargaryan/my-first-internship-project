package com.myproject.myprojec.service;

//import com.myproject.myprojec.csvUpload.csvHelper.UserDetailHelper;
import com.myproject.myprojec.dto.UserDetailDto;
import com.myproject.myprojec.model.QueryResponseWrapper;
import com.myproject.myprojec.persistence.entity.UserDetailEntity;
import com.myproject.myprojec.persistence.rpository.UserDetailRepository;
import com.myproject.myprojec.service.criteria.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailService {

    private final UserDetailRepository userDetailRepository;

    @Autowired
    public UserDetailService(UserDetailRepository userDetailRepository) {
        this.userDetailRepository = userDetailRepository;
    }

    public UserDetailDto createUserDetail(UserDetailDto dto) {
        UserDetailEntity userDetailEntity = new UserDetailEntity();
        UserDetailDto.mapDtoToEntity(dto);
        userDetailEntity = userDetailRepository.save(userDetailEntity);
        return UserDetailDto.mapEntityToDto(userDetailEntity);
    }

    public UserDetailDto getUserDetail(Long id) throws Exception {
        UserDetailEntity userDetailEntity = userDetailRepository.findById(id)
                .orElseThrow(() -> new Exception("User Dedtails not found"));
        return UserDetailDto.mapEntityToDto(userDetailEntity);
    }

    public QueryResponseWrapper<UserDetailDto> getUserDetails(SearchCriteria searchCriteria) {
        Page<UserDetailEntity> page = userDetailRepository.findALLWithPagination(searchCriteria.composePageRequest());
        List<UserDetailEntity> content = page.getContent();
        List<UserDetailDto> dtos = content.stream().map(UserDetailDto::mapEntityToDto).collect(Collectors.toList());
        return new QueryResponseWrapper<>(page.getTotalElements(), dtos);
    }

    public UserDetailDto updateUserDetail(Long id, UserDetailDto dto) throws Exception {
        UserDetailEntity userDetailEntity = userDetailRepository.findById(id)
                .orElseThrow(() -> new Exception("User Dedtails not found"));
        userDetailEntity.setAge(dto.getAge());
        if (dto.getAddress() != null) {
            userDetailEntity.setAddress(dto.getAddress());
        }
//        if (dto.getPhoneNumber() != null) {
            userDetailEntity.setPhoneNumber(dto.getPhoneNumber());
//        }
        userDetailEntity = userDetailRepository.save(userDetailEntity);
        return UserDetailDto.mapEntityToDto(userDetailEntity);
    }

    public void deleteUserDetail(Long id) {
        userDetailRepository.deleteById(id);
    }

//    //for CSV upload
//    public void save(MultipartFile file) {
//        try {
//            List<UserDetailEntity> entities = UserDetailHelper.csvToUserDetailEntity(file.getInputStream());
//            userDetailRepository.saveAll(entities);
//        } catch (IOException e) {
//            throw new RuntimeException("fail to store csv data: " + e.getMessage());
//        }
//    }
//
//    public ByteArrayInputStream load() {
//        List<UserDetailEntity> entities = userDetailRepository.findAll();
//        ByteArrayInputStream in = UserDetailHelper.genreEntityToCSV(entities);
//        return in;
//    }
//
//    public List<UserDetailEntity> getAllUserDetails() {
//        return userDetailRepository.findAll();
//    }
}
