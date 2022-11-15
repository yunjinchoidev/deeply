package com.server.deeply.profile.controller;

import com.server.deeply.profile.dto.ProfileRequestDto;
import com.server.deeply.profile.dto.ProfileResponseDto;
import com.server.deeply.profile.dto.ProfileSearchRequestDto;
import com.server.deeply.profile.service.ProfileService;
import com.server.deeply.user.dto.UserRequestDto;
import com.server.deeply.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProfileController {

    private final ProfileService profileService;

    /**
     * 프로필 정보 조회
     */
    @GetMapping("/profile/{id}")
    public ResponseEntity getProfile(@PathVariable Long id) {
        log.info("ProfileService -> findProfile");
        ProfileRequestDto profileRequestDto = ProfileRequestDto.builder()
                .id(id)
                .build();
        log.info("ProfileService->findProfileById");
        ProfileResponseDto result = profileService.findProfileById(profileRequestDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    /**
     * 프로필 정보 리스트 조회
     */
    @PostMapping("/profile/list")
    public ResponseEntity getProfileList(@RequestBody ProfileSearchRequestDto param) {
        log.info("ProfileService->findAll");
        Page<ProfileResponseDto> result = profileService.searchAll(param);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    /**
     * 프로필 정보 수정
     */
    @PutMapping("/profile")
    public ResponseEntity updateProfile(@RequestBody ProfileRequestDto param) {
        log.info("ProfileService->saveProfile");
        Long profileId = profileService.saveProfile(param);
        HashMap<String,Long> result = new HashMap<>();
        result.put("profileId", profileId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }


// /**
//     * 프로필 정보 저장
//     */
//    @PostMapping("/profile")
//    public ResponseEntity saveProfile(@RequestBody ProfileRequestDto param) {
//        log.info("ProfileService->saveProfile");
//        Long profileId = profileService.saveProfile(param);
//        HashMap<String,Long> result = new HashMap<>();
//        result.put("profileId", profileId);
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(result);
//    }


}
