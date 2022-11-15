package com.server.deeply.code.mapper;

import com.server.deeply.code.dto.CodeRequestDto;
import com.server.deeply.code.dto.CodeResponseDto;
import com.server.deeply.code.jpa.Code;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CodeMapper {

    CodeMapper INSTANCE = Mappers.getMapper(CodeMapper.class);
    Code toEntity(CodeRequestDto codeRequestDto);
    CodeResponseDto toDto(Code code);
}
