package com.server.deeply.board.service;

import com.server.deeply.board.dto.BoardRequestDto;
import com.server.deeply.board.dto.BoardResponseDto;
import com.server.deeply.board.jpa.Board;
import com.server.deeply.board.mapper.BoardMapper;
import com.server.deeply.board.respository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public BoardResponseDto getPost(Long id) {
        Board board = boardRepository.findById(id).get();
        BoardResponseDto result = BoardMapper.INSTANCE.toDto(board);
        return result;
    }

    public Long savePost(BoardRequestDto boardRequestDto) {
        Board board = BoardMapper.INSTANCE.toEntity(boardRequestDto);
        Board save = boardRepository.save(board);
        Long id = save.getId();
        return id;
    }
}
