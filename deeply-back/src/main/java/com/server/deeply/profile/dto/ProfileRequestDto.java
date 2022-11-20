package com.server.deeply.profile.dto;

import com.server.deeply.file.jpa.File;
import com.server.deeply.user.jpa.User;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ProfileRequestDto {

    private Long id; // 프로필 아이디
    private User user = null; // 유저 아이디
//    private Long userId=null;
    private String loc; // 사는 곳
    private Integer age; // 나이
    private String phoneNumber; // 휴대폰 번호
    private String gender; // 성별
    private Integer money; // 재산
    private String childrenYn; // 자녀유무
    private File file;
    private Long fileId;

    private MultipartFile multipartFile;
}
