package com.server.deeply.user.controller;

import com.server.deeply.common.Helper;
import com.server.deeply.common.Response;
import com.server.deeply.config.security.JwtTokenUtil;
import com.server.deeply.config.security.TokenProvider;
import com.server.deeply.user.dto.UserRequestDto;
import com.server.deeply.user.dto.UserResponseDto;
import com.server.deeply.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
public class SignController {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RedisTemplate redisTemplate;
    private final Response response;
    private final JwtTokenUtil jwtTokenUtil;

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

        log.info("userService.getByCredentials");
        UserResponseDto userResponseDto = userService.getByCredentials(userDTO.getEmail(),
                userDTO.getPassword(),
                passwordEncoder);

        // 로그인 성공
        if (userResponseDto != null) {
//            fcmService.saveToken(userDTO);
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


    /**
     * 로그아웃
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@Validated UserRequestDto logout, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        ResponseEntity<?> result = userService.logout(logout);

        return result;
    }


    /**
     * 권한 체크
     * @param request
     * @return
     */
    @GetMapping("/check")
    public ResponseEntity<?> check(HttpServletRequest request) {

        // validation check
        String token = jwtTokenUtil.parseBearerToken(request);
        String email = jwtTokenUtil.getEmailFormToken(token);
        String role = jwtTokenUtil.getRoleFromToken(token);
        UserRequestDto userRequestDto = UserRequestDto.builder().email(email).build();
        log.info("userService.findUserByEmail(userRequestDto)");
        UserResponseDto result = userService.findUserByEmail(userRequestDto);
        result.setIsAuth(true);

        /**
         * 권한 체크
         */
        if ("ROLE_ADMIN".equals(role)) {
            result.setIsAdmin(true);
        } else {
            result.setIsAdmin(false);
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


}
