package com.server.deeply.user.mapper;

import com.server.deeply.user.dto.UserRequestDto;
import com.server.deeply.user.dto.UserResponseDto;
import com.server.deeply.user.jpa.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "encodedPassword", target = "password")
    User toEntity(UserRequestDto userRequestDto);
    UserResponseDto toDto(User user);
    List<UserResponseDto> toDtoList(List<User> users);
}
