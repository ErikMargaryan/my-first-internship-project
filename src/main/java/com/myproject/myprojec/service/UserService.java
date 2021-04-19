package com.myproject.myprojec.service;

import com.myproject.myprojec.service.dto.UserDto;
import com.myproject.myprojec.service.model.QueryResponseWrapper;
import com.myproject.myprojec.persistence.entity.UserEntity;
import com.myproject.myprojec.persistence.rpository.UserRepository;
import com.myproject.myprojec.csvUpload.criteria.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto creatUser(UserDto dto) {
        UserEntity userEntity = new UserEntity();
        UserDto.mapDtoToEntity(dto);

        userEntity = userRepository.save(userEntity);
        return UserDto.mapEntityToDto(userEntity);
    }

    public UserDto getUser(Long id) throws Exception {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found"));
        return UserDto.mapEntityToDto(userEntity);
    }

    public QueryResponseWrapper<UserDto> getUsers(SearchCriteria searchCriteria) {
        Page<UserEntity> page = userRepository.findALLWithPagination(searchCriteria.composePageRequest());
        List<UserEntity> content = page.getContent();
        List<UserDto> dtos = content.stream().map(UserDto::mapEntityToDto).collect(Collectors.toList());
        return new QueryResponseWrapper<>(page.getTotalElements(), dtos);
    }

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
