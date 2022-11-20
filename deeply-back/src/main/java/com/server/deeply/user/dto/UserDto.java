package com.server.deeply.user.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserDto {

    private Long id;
    private String email;
    private String password;
    private String username;
    private String roles;
    private String fcmToken;
    @Builder.Default
    private Integer createId = null; // 생성자 ID

    @Builder.Default
    private LocalDateTime createDt = null; // 생성일시

    @Builder.Default
    private Integer updateId = null; // 수정자 ID

    @Builder.Default
    private LocalDateTime updateDt = null; // 수정일시
}
