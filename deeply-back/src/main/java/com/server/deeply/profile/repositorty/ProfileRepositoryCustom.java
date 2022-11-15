package com.server.deeply.profile.repositorty;

import com.server.deeply.profile.dto.ProfileRequestDto;
import com.server.deeply.profile.dto.ProfileResponseDto;
import com.server.deeply.profile.dto.ProfileSearchRequestDto;
import com.server.deeply.profile.jpa.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProfileRepositoryCustom {
    List<Profile> findAll();

    ProfileResponseDto findById(Long id);

    Page<ProfileResponseDto> searchAll(ProfileSearchRequestDto search, Pageable pageable);

}
