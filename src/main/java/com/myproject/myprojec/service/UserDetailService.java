package com.myproject.myprojec.service;

import com.myproject.myprojec.dto.UserDetailDto;
import com.myproject.myprojec.mapper.UserDetailMapper;
import com.myproject.myprojec.model.entity.UserDetailEntity;
import com.myproject.myprojec.rpository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService {

    private final UserDetailRepository userDetailRepository;

    @Autowired
    public UserDetailService(UserDetailRepository userDetailRepository) {
        this.userDetailRepository = userDetailRepository;
    }

    public UserDetailDto createUserDetail(UserDetailDto dto) {
        UserDetailEntity userDetailEntity = new UserDetailEntity();
        userDetailEntity.setAge(dto.getAge());
        userDetailEntity.setAddress(dto.getAddress());
        userDetailEntity.setPhoneNumber(dto.getPhoneNumber());

        userDetailEntity = userDetailRepository.save(userDetailEntity);
        return UserDetailMapper.mapEntityToDto(userDetailEntity);
    }

    public UserDetailDto getUserDetail(Long id) throws Exception {
        UserDetailEntity userDetailEntity = userDetailRepository.findById(id)
                .orElseThrow(() -> new Exception("User Dedtails not found"));
        return UserDetailMapper.mapEntityToDto(userDetailEntity);
    }

    public UserDetailDto updateUserDetail(Long id, UserDetailDto dto) throws Exception {
        UserDetailEntity userDetailEntity = userDetailRepository.findById(id)
                .orElseThrow(() -> new Exception("User Dedtails not found"));
        userDetailEntity.setAge(dto.getAge());
        if (dto.getAddress() != null) {
            userDetailEntity.setAddress(dto.getAddress());
        }
        if (dto.getPhoneNumber() != null) {
            userDetailEntity.setAddress(dto.getAddress());
        }
        userDetailEntity = userDetailRepository.save(userDetailEntity);
        return UserDetailMapper.mapEntityToDto(userDetailEntity);
    }

    public void deleteUserDetail(Long id) {
        userDetailRepository.deleteById(id);
    }
}
