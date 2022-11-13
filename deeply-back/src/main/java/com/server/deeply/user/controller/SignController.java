package com.server.deeply.user.controller;

import com.server.deeply.config.security.TokenProvider;
import com.server.deeply.user.dto.UserRequestDto;
import com.server.deeply.user.dto.UserResponseDto;
import com.server.deeply.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
public class SignController {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    /**
     * 회원가입
     */
    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody UserRequestDto param) {

        log.info("userService -> saveUser");
        Long userId = userService.saveUser(param);
        UserResponseDto result = UserResponseDto.builder().id(userId).build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(result);
    }

    /**
     * 로그인
     */
    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestBody UserRequestDto userDTO) {

        UserResponseDto userResponseDto = userService.getByCredentials(userDTO.getEmail(),
                                                                        userDTO.getPassword(),
                                                                        passwordEncoder);

        // 로그인 성공
        if (userResponseDto != null) {
            return ResponseEntity.ok().body(userResponseDto);

        // 로그인 실패
        } else {
            UserResponseDto responseDTO = UserResponseDto.builder()
                    .error("Login failed.")
                    .build();
            return ResponseEntity
                    .badRequest()
                    .body(responseDTO);
        }
    }



}
