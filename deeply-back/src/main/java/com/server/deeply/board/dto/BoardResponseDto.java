package com.server.deeply.board.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardResponseDto {

    private Long id;
    private String author;
    private String title;
    private String content;
    private Long fileId;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

}