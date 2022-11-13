package com.server.deeply.profile.mapper;

import com.server.deeply.profile.dto.ProfileRequestDto;
import com.server.deeply.profile.dto.ProfileResponseDto;
import com.server.deeply.profile.jpa.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProfileMapper {

    Profile INSTANCE = Mappers.getMapper(Profile.class);

    Profile toEntity(ProfileRequestDto profileRequestDto);

    ProfileResponseDto toDto(ProfileResponseDto profileResponseDto);
}
