package com.server.deeply.profile.jpa;

import com.server.deeply.common.BaseEntity;
import com.server.deeply.file.jpa.File;
import com.server.deeply.user.jpa.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profile")
@DynamicUpdate
@DynamicInsert
public class Profile extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "profile_id")
    private Long id = null; // 프로필 아이디

    @OneToOne(fetch = FetchType.LAZY,
            optional = false)
    @JoinColumn(name = "user_id")
    private User user = null;   // 유저 아이디

    private String loc = null; // 거주지
    private Integer age = null; // 나이
    private String phoneNumber = null; // 휴대폰번호
    private String gender = null; //  성별
    private Integer money = null; // 자산
    private String childrenYn = null; // 자녀유무

    @OneToOne(fetch = FetchType.LAZY,
            optional = false)
    @JoinColumn(name = "file_id")
    private File file = null;


}
