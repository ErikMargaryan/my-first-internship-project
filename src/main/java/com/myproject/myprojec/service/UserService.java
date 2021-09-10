package com.myproject.myprojec.service;

import com.myproject.myprojec.csvUpload.control.CsvControl;
import com.myproject.myprojec.csvUpload.csvModel.User;
import com.myproject.myprojec.persistence.entity.UserEntity;
import com.myproject.myprojec.persistence.repository.UserRepository;
import com.myproject.myprojec.service.criteria.SearchCriteria;
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
    private final CsvControl<User> csvControl;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, CsvControl<User> csvControl, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.csvControl = csvControl;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity creatUser(UserEntity entity) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(entity.getId());
        userEntity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return userRepository.save(userEntity);
    }

    public UserEntity getById(Long id) throws Exception {
        return userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found"));
    }

    public QueryResponseWrapper<UserEntity> getUsers(SearchCriteria searchCriteria) {
        Page<UserEntity> page = userRepository.findALLWithPagination(searchCriteria.composePageRequest());
        List<UserEntity> content = page.getContent();
        return new QueryResponseWrapper<>(page.getTotalElements(), content);
    }

    public UserEntity updateUser(Long id, UserEntity entity) throws Exception {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found"));
        if (entity.getFirstName() != null) {
            userEntity.setFirstName(entity.getFirstName());
        }
        if (entity.getLastName() != null) {
            userEntity.setLastName(entity.getLastName());
        }
        if (entity.getEmail() != null) {
            userEntity.setEmail(entity.getEmail());
        }
        if (entity.getUsername() != null) {
            userEntity.setUsername(entity.getUsername());
        }
        if (entity.getPassword() != null) {
            userEntity.setPassword(passwordEncoder.encode(entity.getPassword()));
        }
        userEntity = userRepository.save(userEntity);
        return userEntity;
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
