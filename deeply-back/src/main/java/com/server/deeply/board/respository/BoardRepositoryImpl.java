package com.server.deeply.board.respository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.deeply.board.dto.BoardRequestDto;
import com.server.deeply.board.dto.BoardResponseDto;
import com.server.deeply.file.jpa.QFile;
import com.server.deeply.profile.dto.ProfileResponseDto;
import com.server.deeply.profile.dto.ProfileSearchRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.server.deeply.board.jpa.QBoard.board;
import static com.server.deeply.file.jpa.QFile.file;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BoardRepositoryImpl implements BoardCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public BoardResponseDto findByIdCustom(Long id) {
         return jpaQueryFactory.select(
                        Projections.bean(
                                BoardResponseDto.class
                                , board.id
                                , board.author
                                , board.title
                                , board.content
                                , board.fileId
                                , board.createdDate
                                , board.modifiedDate
                        )
                )
                .from(board)
                .where(board.id.eq(id)).fetchOne();
    }

    @Override
    public Page<BoardResponseDto> searchAll(BoardRequestDto boardRequestDto, Pageable pageable) {
        List<BoardResponseDto> content = jpaQueryFactory.select(
                        Projections.bean(
                                BoardResponseDto.class
                                , board.id
                                , board.author
                                , board.title
                                , board.content
                                , board.fileId
                                , board.createdDate
                                , board.modifiedDate
                        ))
                .from(board)
                .leftJoin(file).on(board.fileId.eq(file.id))
                .offset(pageable.getOffset())   // (2) 페이지 번호
                .limit(pageable.getPageSize())  // (3) 페이지 사이즈
                .fetch();

        Long count = jpaQueryFactory        // (4)
                .select(board.count())
                .from(board)
//                .leftJoin(member.team, team)		(5) 검색조건 최적화
                .fetchOne();

        return new PageImpl<>(content, pageable, count);
    }



}
