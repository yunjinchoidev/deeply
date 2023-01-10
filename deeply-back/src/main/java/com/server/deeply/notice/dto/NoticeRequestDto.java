package com.server.deeply.notice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NoticeRequestDto {

    private Long id;

    private String noticeType; // 공지사항 유형
    private String title; // 제목
    private String contents; // 내용
    private String writer; // 글쓴이
    private String noticeYn; // 공지 여부
    private String commentYn; // 댓글 가능 며부
    private String views; // 조회수
    private String goodCnt; // 좋아요 수
    private String badCnt; // 싫어요 수

    private Integer createId = null;
    protected LocalDateTime createDt = null;
    private Integer updateId = null;
    protected LocalDateTime updateDt = null;
}
