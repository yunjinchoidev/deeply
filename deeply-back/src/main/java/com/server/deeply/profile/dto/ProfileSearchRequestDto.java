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
public class ProfileSearchRequestDto {

    private Long id;
    private User user;
    private String loc;
    private Integer age;
    private String phoneNumber;
    private String gender;
    private Integer money;
    private String childrenYn; // 자녀유무

    private Integer page;
    private Integer pageSize;
    private String orderBy;

}
