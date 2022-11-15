package com.server.deeply.profile.jpa;

import com.server.deeply.common.BaseEntity;
import com.server.deeply.user.jpa.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profile")
public class Profile extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "profile_id")
    private Long id; // 프로필 아이디

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;   // 유저 아이디

    private String loc; // 거주지
    private Integer age; // 나이
    private String phoneNumber; // 휴대폰번호
    private String gender; // 성별
    private Integer money; // 자산
    private String childrenYn; // 자녀유무


}
