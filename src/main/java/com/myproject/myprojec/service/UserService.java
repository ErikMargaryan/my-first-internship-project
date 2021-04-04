package com.myproject.myprojec.service;

import com.myproject.myprojec.dto.UserDto;
//import com.myproject.myprojec.mapper.UserMapper;
import com.myproject.myprojec.model.QueryResponseWrapper;
//import com.myproject.myprojec.model.UserWrapper;
import com.myproject.myprojec.model.entity.UserEntity;
import com.myproject.myprojec.rpository.UserRepository;
import com.myproject.myprojec.service.criteria.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto creatUser(UserDto dto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(dto.getFirstName());
        userEntity.setLastName(dto.getLastName());
        userEntity.setEmail(dto.getEmail());
        userEntity.setUsername(dto.getUsername());
        userEntity.setPassword(dto.getPassword());

        userEntity=userRepository.save(userEntity);
        return UserDto.mapEntityToDto(userEntity);
    }

    public UserDto getUser(Long id) throws Exception {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found"));
        return UserDto.mapEntityToDto(userEntity);
    }

//    public QueryResponseWrapper<UserWrapper> getUsers(SearchCriteria searchCriteria) {
//        Page<UserWrapper> content = userRepository.findALLWithPagination(searchCriteria.composePageRequest());
//        return new QueryResponseWrapper<>(content.getTotalElements(), content.getContent());
//    }

    public UserDto updateUser(Long id, UserDto dto) throws Exception {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found"));
        if (dto.getFirstName() != null) {
            userEntity.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            userEntity.setLastName(dto.getLastName());
        }
        if (dto.getEmail() != null) {
            userEntity.setEmail(dto.getEmail());
        }
        if (dto.getUsername() != null) {
            userEntity.setUsername(dto.getUsername());
        }
        if (dto.getPassword() != null) {
            userEntity.setPassword(dto.getPassword());
        }
        userEntity = userRepository.save(userEntity);
        return UserDto.mapEntityToDto(userEntity);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
