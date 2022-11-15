package com.server.deeply.board.mapper;

import com.server.deeply.board.dto.BoardRequestDto;
import com.server.deeply.board.dto.BoardResponseDto;
import com.server.deeply.board.jpa.Board;
import com.server.deeply.file.dto.FileRequestDto;
import com.server.deeply.file.dto.FileResponseDto;
import com.server.deeply.file.mapper.FileMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BoardMapper {

    BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);
    Board toEntity(BoardRequestDto profileRequestDto);
    BoardResponseDto toDto(Board file);
}
