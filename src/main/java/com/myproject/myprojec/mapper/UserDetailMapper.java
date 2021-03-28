package com.myproject.myprojec.mapper;

import com.myproject.myprojec.dto.UserDetailDto;
import com.myproject.myprojec.model.entity.UserDetailEntity;

public class UserDetailMapper {
    public static UserDetailDto mapEntityToDto(UserDetailEntity entity) {
        if (entity == null) {
            return null;
        }
        UserDetailDto dto = new UserDetailDto();
        dto.setId(entity.getId());
        dto.setAge(entity.getAge());
        dto.setAddress(entity.getAddress());
        dto.setPhoneNumber(entity.getPhoneNumber());
        return dto;
    }
}
