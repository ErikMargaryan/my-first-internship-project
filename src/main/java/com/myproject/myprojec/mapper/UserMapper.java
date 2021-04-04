package com.myproject.myprojec.mapper;

import com.myproject.myprojec.dto.UserDto;
import com.myproject.myprojec.model.entity.UserEntity;
import com.myproject.myprojec.model.entity.UserRatedBookEntity;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserDto mapEntityToDto(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        List<UserRatedBookEntity> userRatedBookEntityList = entity.getUsersRatedBooksList();
        if (!CollectionUtils.isEmpty(userRatedBookEntityList)) {
            dto.setUsersRatedBooksDtoList(userRatedBookEntityList.stream().map(UsersRatedBooksMapper::mapEntityToDto).collect(Collectors.toList()));
        }
        return dto;
    }
}
