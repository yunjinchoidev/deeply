package com.server.deeply.board.service;

import com.server.deeply.board.dto.BoardRequestDto;
import com.server.deeply.board.dto.BoardResponseDto;
import com.server.deeply.board.jpa.Board;
import com.server.deeply.board.mapper.BoardMapper;
import com.server.deeply.board.respository.BoardRepository;
import com.server.deeply.board.respository.BoardRepositoryImpl;
import com.server.deeply.file.dto.FileRequestDto;
import com.server.deeply.file.dto.FileResponseDto;
import com.server.deeply.file.jpa.File;
import com.server.deeply.file.mapper.FileMapper;
import com.server.deeply.file.repositorty.FileRepository;
import com.server.deeply.profile.dto.ProfileRequestDto;
import com.server.deeply.profile.dto.ProfileResponseDto;
import com.server.deeply.profile.dto.ProfileSearchRequestDto;
import com.server.deeply.profile.jpa.Profile;
import com.server.deeply.profile.mapper.ProfileMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final FileRepository fileRepository;

    @Transactional
    public BoardResponseDto getPost(BoardRequestDto boardRequestDto) {
        Board board = boardRepository.findById(boardRequestDto.getId()).get();
        File file = fileRepository.findById(board.getFileId()).get();
        BoardResponseDto result = BoardMapper.INSTANCE.toDto(board);
        FileResponseDto fileResponseDto = FileMapper.INSTANCE.toDto(file);

        result.setFileResponseDto(fileResponseDto);
        return result;
    }

    public Long saveBoard(BoardRequestDto boardRequestDto) {
        Board board = BoardMapper.INSTANCE.toEntity(boardRequestDto);
        Board save = boardRepository.save(board);
        Long id = save.getId();
        return id;
    }


    @Transactional(readOnly = true)
    public Page<BoardResponseDto> searchAll(BoardRequestDto param){
        Sort sort = Sort.by(param.getOrderBy()).ascending();
        Pageable pageable = PageRequest.of(param.getPage(), param.getPageSize(), sort);
        Page<BoardResponseDto> result = boardRepository.searchAll(param, pageable);
        return result;
    }
}
