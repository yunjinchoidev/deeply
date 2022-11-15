package com.server.deeply.code.jpa;


import com.server.deeply.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "code")
public class Code extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "code_id")
    @Order(value = 1)
    private Long id;

    @Order(value = 2)
    private String parentCode; // 부모 코드
    @Order(value = 3)
    private String parentCodeName; // 부모 코드명

    @Order(value = 4)
    private String childCode; // 자식 코드
    @Order(value = 5)
    private String childCodeName; // 자식 코드명

}
