package com.myproject.myprojec.service.dto;

import com.myproject.myprojec.persistence.entity.RoleEntity;
import com.myproject.myprojec.persistence.entity.UserRoleEntity;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class RoleDto {
    private Long id;
    private String name;
    private List<UserRoleDto> listOfUserRole;

    public RoleDto() {
    }

    public RoleDto(Long id, String name, List<UserRoleDto> listOfUserRole) {
        this.id = id;
        this.name = name;
        this.listOfUserRole = listOfUserRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserRoleDto> getListOfUserRole() {
        return listOfUserRole;
    }

    public void setListOfUserRole(List<UserRoleDto> listOfUserRole) {
        this.listOfUserRole = listOfUserRole;
    }

    public static RoleDto mapEntityToDto(RoleEntity entity) {
        if (entity == null) {
            return null;
        }
        RoleDto dto = new RoleDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        List<UserRoleEntity> userRoleEntityList = entity.getListOfUserRole();
        if (!CollectionUtils.isEmpty(userRoleEntityList)) {
            dto.setListOfUserRole(userRoleEntityList.stream().map(UserRoleDto::mapEntityToDto).collect(Collectors.toList()));
        }
        return dto;
    }

    public static RoleEntity mapDtoToEntity(RoleDto dto) {
        if (dto == null) {
            return null;
        }
        RoleEntity entity = new RoleEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        List<UserRoleDto> userRoleDtoList = dto.getListOfUserRole();
        if (!CollectionUtils.isEmpty(userRoleDtoList)) {
            entity.setListOfUserRole(userRoleDtoList.stream().map(UserRoleDto::mapDtoToEntity).collect(Collectors.toList()));
        }
        return entity;
    }
}
