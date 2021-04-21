//package com.myproject.myprojec.service.dto;
//
//import com.myproject.myprojec.persistence.entity.RoleEntity;
//import com.myproject.myprojec.persistence.entity.UserEntity;
//import com.myproject.myprojec.persistence.entity.UserRoleEntity;
//
//public class UserRoleDto {
//
//    private Long id;
//
//    private UserEntity user;
//
//    private RoleEntity role;
//
//    public UserRoleDto() {
//    }
//
//    public UserRoleDto(Long id, UserEntity user, RoleEntity role) {
//        this.id = id;
//        this.user = user;
//        this.role = role;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public UserEntity getUser() {
//        return user;
//    }
//
//    public void setUser(UserEntity user) {
//        this.user = user;
//    }
//
//    public RoleEntity getRole() {
//        return role;
//    }
//
//    public void setRole(RoleEntity role) {
//        this.role = role;
//    }
//
//    public static UserRoleEntity mapDtoToEntity(UserRoleDto dto) {
//        if (dto == null) {
//            return null;
//        }
//        UserRoleEntity entity = new UserRoleEntity();
//        entity.setId(dto.getId());
//        entity.setRole(dto.getRole());
//        entity.setUser(dto.getUser());
//        return entity;
//    }
//
//    public static UserRoleDto mapEntityToDto(UserRoleEntity entity) {
//        if (entity == null) {
//            return null;
//        }
//        UserRoleDto dto = new UserRoleDto();
//        dto.setId(entity.getId());
//        dto.setRole(entity.getRole());
//        dto.setUser(entity.getUser());
//        return dto;
//    }
//
//}
