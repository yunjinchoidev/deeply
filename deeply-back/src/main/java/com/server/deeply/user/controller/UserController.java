package com.server.deeply.user.controller;

import com.server.deeply.config.security.TokenProvider;
import com.server.deeply.user.dto.UserRequestDto;
import com.server.deeply.user.jpa.User;
import com.server.deeply.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/user/list")
    public ResponseEntity getUserList(@PathVariable Long userId) {
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

}
