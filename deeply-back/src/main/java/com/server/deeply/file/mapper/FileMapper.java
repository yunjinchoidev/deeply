package com.server.deeply.file.mapper;

import com.server.deeply.file.dto.FileRequestDto;
import com.server.deeply.file.dto.FileResponseDto;
import com.server.deeply.file.jpa.File;
import com.server.deeply.profile.dto.ProfileRequestDto;
import com.server.deeply.profile.dto.ProfileResponseDto;
import com.server.deeply.profile.mapper.ProfileMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FileMapper {
    FileMapper INSTANCE = Mappers.getMapper(FileMapper.class);
    File toEntity(FileRequestDto profileRequestDto);
    FileResponseDto toDto(File file);
}
