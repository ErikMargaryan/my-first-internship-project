package com.myproject.myprojec.service.dto;

import com.myproject.myprojec.persistence.entity.UserEntity;
import com.myproject.myprojec.persistence.entity.UserRoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDto {
    private Long id;
    private UserDto user;
    private RoleDto role;

    public static UserRoleDto mapEntityToDto(UserRoleEntity entity) {
        if (entity == null) {
            return null;
        }
        UserRoleDto dto = new UserRoleDto();
        dto.setId(entity.getId());
        entity = new UserRoleEntity(entity.getId(), entity.getUser(), entity.getRole());
        dto.setUser(UserDto.mapEntityToDto(entity.getUser()));
        dto.setRole(RoleDto.mapEntityToDto(entity.getRole()));
        return dto;
    }

    public static UserRoleEntity mapDtoToEntity(UserRoleDto dto) {
        if (dto == null) {
            return null;
        }
        UserRoleEntity entity = new UserRoleEntity();
        entity.setId(dto.getId());
        dto = new UserRoleDto(dto.getId(), dto.getUser(), dto.getRole());
        entity.setUser(UserDto.mapDtoToEntity(dto.getUser(), new UserEntity()));
        entity.setRole(RoleDto.mapDtoToEntity(dto.getRole()));
        return entity;
    }
}
