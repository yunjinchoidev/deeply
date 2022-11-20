package com.server.deeply.profile.service;

import com.server.deeply.file.jpa.File;
import com.server.deeply.file.service.FileService;
import com.server.deeply.file.service.UploadService;
import com.server.deeply.profile.dto.ProfileRequestDto;
import com.server.deeply.profile.dto.ProfileResponseDto;
import com.server.deeply.profile.dto.ProfileSearchRequestDto;
import com.server.deeply.profile.jpa.Profile;
import com.server.deeply.profile.mapper.ProfileMapper;
import com.server.deeply.profile.repositorty.ProfileRepository;
import com.server.deeply.profile.repositorty.ProfileRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileRepositoryImpl profileCustomRepository;
    private final FileService fileService;
    private final UploadService uploadService;

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

    public ProfileResponseDto findProfileByUserId(ProfileRequestDto profileRequestDto) {
        ProfileResponseDto result = profileCustomRepository.findByUserId(profileRequestDto.getUser().getId());
        return result;
    }

    /**
     * 프로필 검색 페이징
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public Page<ProfileResponseDto> searchAll(ProfileSearchRequestDto param) {
        Sort sort = Sort.by(param.getOrderBy()).ascending();
        Pageable pageable = PageRequest.of(param.getPage(), param.getPageSize(), sort);
        Page<ProfileResponseDto> result = profileCustomRepository.searchAll(param, pageable);
        return result;
    }

    @Transactional
    public Long saveProfile(ProfileRequestDto profileRequestDto) {
        if (null != profileRequestDto.getMultipartFile()) {
            HashMap<String, Long> upload = uploadService.upload(profileRequestDto.getMultipartFile());
            Long fileId = upload.get("fileId");
            File file = File.builder().id(fileId).build();
            profileRequestDto.setFile(file);
        }
        Profile profile = ProfileMapper.INSTANCE.toEntity(profileRequestDto);
        Profile save = profileRepository.save(profile);
        return save.getId();
    }


}
