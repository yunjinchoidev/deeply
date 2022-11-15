package com.server.deeply.board.respository;

import com.server.deeply.board.dto.BoardRequestDto;
import com.server.deeply.board.dto.BoardResponseDto;
import com.server.deeply.board.jpa.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, BoardCustomRepository {
}
