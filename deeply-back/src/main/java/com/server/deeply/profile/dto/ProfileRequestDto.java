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

    private Long id;
    private User user;
    private String loc;
    private String age;
    private String phoneNumber;
    private String gender;
    private Integer money;
    private Integer childrenYn; // 자녀유무

}
