package com.server.deeply.board.respository;

import com.server.deeply.board.dto.BoardRequestDto;
import com.server.deeply.board.dto.BoardResponseDto;
import com.server.deeply.profile.dto.ProfileResponseDto;
import com.server.deeply.profile.dto.ProfileSearchRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardCustomRepository {


    BoardResponseDto findByIdCustom(Long id);

    Page<BoardResponseDto> searchAll(BoardRequestDto boardRequestDto, Pageable pageable);


}
