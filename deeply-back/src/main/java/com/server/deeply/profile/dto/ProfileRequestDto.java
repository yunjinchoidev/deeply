package com.server.deeply.profile.dto;

import com.server.deeply.user.jpa.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileRequestDto {

    private Long id; // 프로필 아이디
    private Long userId; // 유저 아이디
    private User user; // 유저 아이디

    private String loc; // 사는 곳
    private Integer age; // 나이
    private String phoneNumber; // 휴대폰 번호
    private String gender; // 성별
    private Integer money; // 재산
    private String childrenYn; // 자녀유무

}
