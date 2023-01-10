package com.server.deeply.notice.mapper;

import com.server.deeply.notice.dto.NoticeRequestDto;
import com.server.deeply.notice.dto.NoticeResponseDto;
import com.server.deeply.notice.jpa.Notice;
import com.server.deeply.user.dto.UserRequestDto;
import com.server.deeply.user.dto.UserResponseDto;
import com.server.deeply.user.jpa.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface NoticeMapper {
    NoticeMapper INSTANCE = Mappers.getMapper(NoticeMapper.class);

    Notice toEntity(NoticeRequestDto userRequestDto);
    NoticeResponseDto toDto(Notice notice);
}
