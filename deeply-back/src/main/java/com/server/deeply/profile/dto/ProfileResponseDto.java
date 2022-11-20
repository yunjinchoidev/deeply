package com.server.deeply.profile.dto;

import com.server.deeply.file.dto.FileResponseDto;
import com.server.deeply.file.jpa.File;
import com.server.deeply.profile.jpa.Profile;
import com.server.deeply.user.jpa.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ProfileResponseDto {

    private Long id; // 프로필 아이디
    private User user; // 유저 아이디
    private String loc; // 거주지
    private Integer age; // 나이
    private String phoneNumber; // 휴대폰 번호
    private String gender; // 성별
    private Integer money; // 재산
    private String childrenYn; // 자녀유무

    private String email;
    private String username;
    private String role;

    private File file;

}
