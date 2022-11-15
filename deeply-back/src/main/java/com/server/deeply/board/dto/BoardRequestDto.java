package com.server.deeply.board.dto;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardRequestDto {

    private Long id;
    private String author;
    private String title;
    private String content;
    private Long fileId;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

}