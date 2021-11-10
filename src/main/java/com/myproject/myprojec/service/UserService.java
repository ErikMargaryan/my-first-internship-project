package com.myproject.myprojec.service;

import com.myproject.myprojec.csvUpload.control.CsvControl;
import com.myproject.myprojec.persistence.entity.RoleEntity;
import com.myproject.myprojec.persistence.repository.RoleRepository;
import com.myproject.myprojec.service.criteria.SearchCriteria;
import com.myproject.myprojec.csvUpload.csvModel.User;
import com.myproject.myprojec.persistence.entity.UserEntity;
import com.myproject.myprojec.persistence.repository.UserRepository;
import com.myproject.myprojec.service.dto.RoleDto;
import com.myproject.myprojec.service.dto.UserDto;
import com.myproject.myprojec.service.dto.UserRoleDto;
import com.myproject.myprojec.service.model.QueryResponseWrapper;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CsvControl<User> csvControl;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, CsvControl<User> csvControl, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.csvControl = csvControl;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto createUser(UserDto dto) {
        UserEntity userEntity = UserDto.mapDtoToEntity(dto, new UserEntity());
//        userEntity.setId(dto.getId());
        userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        RoleDto role = new RoleDto();
        role.setName(dto.getUserRoleDtoList().stream()
                .map(UserRoleDto::getRole)
                .map(RoleDto::getName)
                .collect(Collectors.joining(", "))
        );
        RoleEntity roleEntity = RoleDto.mapDtoToEntity(role);
        roleEntity = roleRepository.save(roleEntity);
        UserEntity entity = userRepository.save(userEntity);

        return UserDto.mapEntityToDto(entity);
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
            userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        userEntity = userRepository.save(userEntity);
        return UserDto.mapEntityToDto(userEntity);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void parseCsv(MultipartFile file) throws NotFoundException {
        List<List<User>> users = csvControl.getEntitiesFromCsv(file, User.class);
        List<List<UserEntity>> usersList = users.stream().map(user -> user.stream()
                .map(User::mapCsvToEntity)
                .collect(Collectors.toList()))
                .collect(Collectors.toList());
        usersList.forEach(userRepository::saveAll);
    }

}
