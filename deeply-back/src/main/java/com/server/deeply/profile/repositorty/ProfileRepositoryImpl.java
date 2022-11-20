package com.server.deeply.profile.repositorty;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.deeply.profile.dto.ProfileResponseDto;
import com.server.deeply.profile.dto.ProfileSearchRequestDto;
import com.server.deeply.profile.jpa.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.List;
import static com.server.deeply.profile.jpa.QProfile.profile;
import static com.server.deeply.user.jpa.QUser.user;

@Repository
@RequiredArgsConstructor
public class ProfileRepositoryImpl implements ProfileRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<Profile> findAll() {
        return jpaQueryFactory.select(
                        Projections.bean(Profile.class,
                                profile.id,
                                profile.gender))
                .from(profile).fetch();
    }

    @Override
    public ProfileResponseDto findById(Long id) {
        return jpaQueryFactory.select(
                        Projections.bean(
                                ProfileResponseDto.class
                                , profile.id
                                , profile.user.id
                                , profile.user.email
                                , profile.gender
                                , profile.age
                                , profile.loc
                                , profile.childrenYn
                                , profile.money
                                , profile.phoneNumber
                                , profile.file
                        )
                )
                .from(profile)
                .where(profile.id.eq(id)).fetchOne();
    }


     @Override
    public ProfileResponseDto findByUserId(Long id) {
        return jpaQueryFactory.select(
                        Projections.bean(
                                ProfileResponseDto.class
                                , profile.id
                                , profile.gender
                                , profile.age
                                , profile.loc
                                , profile.childrenYn
                                , profile.money
                                , profile.phoneNumber
                                , profile.file
                                , profile.user.email
                                , profile.user.role
                        )
                )
                .from(profile)
                .where(profile.user.id.eq(id)).fetchOne();

    }
    @Override
    public Page<ProfileResponseDto> searchAll(ProfileSearchRequestDto search, Pageable pageable) {

        List<ProfileResponseDto> content = jpaQueryFactory.select(
                        Projections.bean(
                                ProfileResponseDto.class
                                , profile.id
                                , profile.gender
                                , profile.age
                                , profile.loc
                                , profile.childrenYn
                                , profile.money
                                , profile.phoneNumber
                                , user.email
                                , user.role
                                , user.username
                        ))
                .from(profile)
                .leftJoin(user).on(profile.user.id.eq(user.id))
                .offset(pageable.getOffset())   // (2) 페이지 번호
                .limit(pageable.getPageSize())  // (3) 페이지 사이즈
                .fetch();

        Long count = jpaQueryFactory        // (4)
                .select(profile.count())
                .from(profile)
//                .leftJoin(member.team, team)		(5) 검색조건 최적화
                .fetchOne();

        return new PageImpl<>(content, pageable, count);    // (6) PageImpl 반환
    }

}
