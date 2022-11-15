package com.server.deeply.board.dto;


import com.server.deeply.file.dto.FileResponseDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class BoardResponseDto {

    private Long id;
    private String author;
    private String title;
    private String content;
    private Long fileId;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    private FileResponseDto fileResponseDto;

}