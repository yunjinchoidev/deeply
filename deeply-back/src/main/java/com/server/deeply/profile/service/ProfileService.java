package com.server.deeply.profile.service;

import com.server.deeply.profile.dto.ProfileRequestDto;
import com.server.deeply.profile.dto.ProfileResponseDto;
import com.server.deeply.profile.dto.ProfileSearchRequestDto;
import com.server.deeply.profile.jpa.Profile;
import com.server.deeply.profile.mapper.ProfileMapper;
import com.server.deeply.profile.repositorty.ProfileRepositoryImpl;
import com.server.deeply.profile.repositorty.ProfileRepository;
import com.server.deeply.user.dto.UserResponseDto;
import com.server.deeply.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileRepositoryImpl profileCustomRepository;

    /**
     * 프로필 조회
     *
     * @param profileRequestDto
     * @return
     */
    public ProfileResponseDto findProfileById(ProfileRequestDto profileRequestDto) {
        ProfileResponseDto result = profileCustomRepository.findById(profileRequestDto.getId());
        return result;
    }

    /**
     * 프로필 검색 페이징
     *
     * @param search
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    public Page<ProfileResponseDto> searchAll(ProfileSearchRequestDto param){

        Sort sort = Sort.by(param.getOrderBy()).ascending();
        Pageable pageable = PageRequest.of(param.getPage(), param.getPageSize(), sort);

//        List<User> userList = userRepository.findAll();
//
//        List<UserResponseDto> userResponseDtos = UserMapper.INSTANCE.toDtoList(userList);
//
//        Page<UserResponseDto> result =
//                new PageImpl<>(userResponseDtos, pageable, userList.size());
//        PageRequest of = PageRequest.of(param.getPage(), param.getPageSize());{

        Page<ProfileResponseDto> result = profileCustomRepository.searchAll(param, pageable);
        return result;
    }

    public Long saveProfile(ProfileRequestDto profileRequestDto) {
        Profile profile = ProfileMapper.INSTANCE.toEntity(profileRequestDto);
        Profile save = profileRepository.save(profile);
        return save.getId();
    }


}
