package com.server.deeply.user.jpa;

import com.server.deeply.common.BaseEntity;
import com.server.deeply.user.dto.UserRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@DynamicUpdate
@DynamicInsert
@Document(indexName = "users")
public class User extends BaseEntity implements UserDetails, CredentialsContainer {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String email; // 이메일
    private String password; // 비밀번호
    private String role; // 권한
    private String username; // 이름

    @Column(name = "fcm_token")
    private String fcmToken;



    public List<String> getRoleList() {
        if (role.length() > 0) {
            return Arrays.asList(role.split(","));
        }
        return new ArrayList<>();
    }

    //해당 User의 권한을 리턴하는 곳!
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return getRole();
            }
        });
        return collection;
    }
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void eraseCredentials() {

    }


     public void updateInfo(UserRequestDto requestDto) {
        if (ObjectUtils.isEmpty(requestDto))
            throw new IllegalArgumentException("요청 파라미터가 NULL입니다.");

        this.username = requestDto.getUsername();
        this.email = requestDto.getEmail();
        this.password = requestDto.getPassword();
        this.role = requestDto.getRole();
        this.fcmToken = requestDto.getFcmToken();
    }


}

