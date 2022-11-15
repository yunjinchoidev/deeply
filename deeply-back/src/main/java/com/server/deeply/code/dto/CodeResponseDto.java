package com.server.deeply.code.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CodeResponseDto {

    private Long id;

    private String parentCode; // 부모 코드
    private String parentCodeName; // 부모 코드명

    private String childCode; // 자식 코드
    private String childCodeName; // 자식 코드명
}
