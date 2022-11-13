package com.server.deeply.user.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRequestDto {

    private Long id;
    private String email;
    private String password;
    private String encodedPassword;

    private String username;
    private String role;

    @Builder.Default
    private Integer createId = null; // 생성자 ID
    @Builder.Default
    private LocalDateTime createDt = null; // 생성일시
    @Builder.Default
    private Integer updateId = null; // 수정자 ID
    @Builder.Default
    private LocalDateTime updateDt = null; // 수정일시
}
