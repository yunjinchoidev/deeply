package com.server.deeply.user.controller;

import com.server.deeply.config.security.JwtTokenUtil;
import com.server.deeply.config.security.TokenProvider;
import com.server.deeply.user.dto.UserRequestDto;
import com.server.deeply.user.dto.UserResponseDto;
import com.server.deeply.user.jpa.User;
import com.server.deeply.user.service.UserServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.awt.print.Pageable;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final JwtTokenUtil jwtTokenUtil;

    /**
     * 회원 정보 조회
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity getUser(
            HttpServletRequest request,
            @PathVariable Long userId) {
        String token = jwtTokenUtil.parseBearerToken(request);
        String email = jwtTokenUtil.getEmailFormToken(token);
        String role = jwtTokenUtil.getRoleFromToken(token);

//        if(null == role || "ROLE_USER".equals(role)){
//            throw new IllegalStateException("잘못된 접근입니다.");
//        }


        log.info("userService -> findUser");
        UserRequestDto userRequestDto = UserRequestDto.builder()
                .id(userId)
                .build();
        log.info("userService->findUserById");
        UserResponseDto result = userService.findById(userRequestDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    /**
     * 회원 정보 리스트 조회
     */
    @PostMapping("/user/list")
    public ResponseEntity getUserList(@RequestBody UserRequestDto param) {
        log.info("userService->findAll");
        Page<UserResponseDto> result = this.userService.findAll(param);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    /**
     * 회원 정보 수정
     */
    @PutMapping("/user")
    public ResponseEntity updateUser(@RequestBody UserRequestDto param) {
        log.info("userService->updateUser");
        UserResponseDto result = this.userService.updateUser(param);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }


}
