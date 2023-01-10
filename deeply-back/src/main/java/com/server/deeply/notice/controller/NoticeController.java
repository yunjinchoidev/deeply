package com.server.deeply.notice.controller;

import com.server.deeply.board.dto.BoardResponseDto;
import com.server.deeply.config.security.JwtTokenUtil;
import com.server.deeply.config.security.TokenProvider;
import com.server.deeply.notice.dto.NoticeRequestDto;
import com.server.deeply.notice.dto.NoticeResponseDto;
import com.server.deeply.notice.service.NoticeService;
import com.server.deeply.user.dto.UserRequestDto;
import com.server.deeply.user.dto.UserResponseDto;
import com.server.deeply.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;


    @PostMapping("/api/notice")
    public ResponseEntity SaveNotice(
            @RequestBody NoticeRequestDto param){

        log.info("noticeService->saveNotice");
        NoticeResponseDto result = noticeService.saveNotice(param);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }


}
