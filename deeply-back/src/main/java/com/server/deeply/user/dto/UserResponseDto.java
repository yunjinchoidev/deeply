package com.server.deeply.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserResponseDto {

    private Long id;
    private String email;
    private String password;

    private String username;
    private String role;

    private String accessToken;
    private String refreshToken;
    private String error;

    @Builder.Default
    private Integer createId = null; // 생성자 ID
    @Builder.Default
    private LocalDateTime createDt = null; // 생성일시
    @Builder.Default
    private Integer updateId = null; // 수정자 ID
    @Builder.Default
    private LocalDateTime updateDt = null; // 수정일시
}
