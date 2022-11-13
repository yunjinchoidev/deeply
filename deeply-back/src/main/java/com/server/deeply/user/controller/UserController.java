package com.server.deeply.user.controller;

import com.server.deeply.config.security.TokenProvider;
import com.server.deeply.user.dto.UserRequestDto;
import com.server.deeply.user.dto.UserResponseDto;
import com.server.deeply.user.jpa.User;
import com.server.deeply.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    /**
     * 회원 정보 조회
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity getUser(@PathVariable Long userId) {
        log.info("userService -> findUser");
        UserRequestDto userRequestDto = UserRequestDto.builder()
                .id(userId)
                .build();
        log.info("userService->findUserById");
        Optional<User> result = userService.findUserById(userRequestDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    /**
     * 회원 정보 리스트 조회
     */
    @PutMapping("/user/list")
    public ResponseEntity getUserList(@RequestBody UserRequestDto param) {
        log.info("userService->findAll");
        Page<User> result = this.userService.findAll(param);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    /**
     * 회원 정보 수정
     */
    @PutMapping("/user")
    public ResponseEntity updateUser(@RequestBody UserRequestDto param) {
        log.info("userService->findAll");
        UserResponseDto result = this.userService.updateUser(param);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }


}
