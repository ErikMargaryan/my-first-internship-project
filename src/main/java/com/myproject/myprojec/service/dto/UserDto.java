package com.myproject.myprojec.service.dto;

import com.myproject.myprojec.persistence.entity.RoleEntity;
import com.myproject.myprojec.persistence.entity.UserEntity;
import com.myproject.myprojec.persistence.entity.UserRatedBookEntity;
import com.myproject.myprojec.persistence.entity.UserRoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String address;
    private String phoneNumber;
    private String email;
    private String username;
    private String password;
    private List<UserRatedBookDto> userRatedBookDtoList;
    private List<UserRoleDto> userRoleDtoList;
//    private List<String> userRoleDtoList;
//    private String role;

    public static UserDto mapEntityToDto(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setAge(entity.getAge());
        dto.setAddress(entity.getAddress());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setEmail(entity.getEmail());
        dto.setUsername(entity.getUsername());
        List<UserRatedBookEntity> userRatedBookEntityList = entity.getUserRatedBookEntityList();
        if (!CollectionUtils.isEmpty(userRatedBookEntityList)) {
            dto.setUserRatedBookDtoList(userRatedBookEntityList.stream().map(UserRatedBookDto::mapEntityToDto).collect(Collectors.toList()));
        }
        List<UserRoleEntity> userRoleEntityList = entity.getListOfUserRole();
        if (!CollectionUtils.isEmpty(userRoleEntityList)) {
            dto.setUserRoleDtoList(userRoleEntityList.stream().map(UserRoleDto::mapEntityToDto).collect(Collectors.toList()));
        }

//        List<UserRoleEntity> userRoleEntityList = entity.getListOfUserRole();
//        if (!CollectionUtils.isEmpty(userRoleEntityList)) {
//            dto.setUserRoleDtoList(userRoleEntityList.stream().map(UserRoleEntity::getRole)
//                    .map(RoleEntity::getName)
//                    .collect(Collectors.toList()));
//        }

//        dto.setRole(getRolesFromEntity(entity));
        return dto;
    }

    public static UserEntity mapDtoToEntity(UserDto dto, UserEntity entity) {
        if (dto == null) {
            return null;
        }
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setAge(dto.getAge());
        entity.setAddress(dto.getAddress());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setEmail(dto.getEmail());
        entity.setUsername(dto.getUsername());
        List<UserRatedBookDto> userRatedBookDtoList = dto.getUserRatedBookDtoList();
        if (!CollectionUtils.isEmpty(userRatedBookDtoList)) {
            entity.setUserRatedBookEntityList(userRatedBookDtoList.stream().map(UserRatedBookDto::mapDtoToEntity).collect(Collectors.toList()));
        }
        List<UserRoleDto> userRoleDtoList = dto.getUserRoleDtoList();
        if (!CollectionUtils.isEmpty(userRoleDtoList)) {
            entity.setListOfUserRole(userRoleDtoList.stream().map(UserRoleDto::mapDtoToEntity).collect(Collectors.toList()));
        }

        return entity;
    }

//    private static String getRolesFromEntity(UserEntity entity) {
//        if (entity.getListOfUserRole() == null) {
//            return "USER";
//        }
//        return entity.getListOfUserRole().stream()
//                .map(UserRoleEntity::getRole)
//                .map(RoleEntity::getName)
//                .collect(Collectors.joining(", "));
//    }
}
