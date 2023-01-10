package com.server.deeply.notice.service;

import com.server.deeply.notice.dto.NoticeRequestDto;
import com.server.deeply.notice.dto.NoticeResponseDto;
import com.server.deeply.notice.jpa.Notice;
import com.server.deeply.notice.mapper.NoticeMapper;
import com.server.deeply.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public NoticeResponseDto saveNotice(NoticeRequestDto param) {
        Notice notice = NoticeMapper.INSTANCE.toEntity(param);
        Notice saveResult = noticeRepository.save(notice);
        NoticeResponseDto noticeResponseDto = NoticeMapper.INSTANCE.toDto(saveResult);
        return noticeResponseDto;
    }


}
