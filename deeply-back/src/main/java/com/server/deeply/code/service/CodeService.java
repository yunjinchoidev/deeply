package com.server.deeply.code.service;

import com.server.deeply.code.dto.CodeRequestDto;
import com.server.deeply.code.dto.CodeResponseDto;
import com.server.deeply.code.jpa.Code;
import com.server.deeply.code.mapper.CodeMapper;
import com.server.deeply.code.repository.CodeRepository;
import com.server.deeply.code.repository.CodeRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CodeService {

    private final CodeRepository codeRepository;
    private final CodeRepositoryImpl codeRepositoryImpl;

    public Long saveCode(CodeRequestDto codeRequest) {
        Code code = CodeMapper.INSTANCE.toEntity(codeRequest);
        Code save = codeRepository.save(code);

        return save.getId();
    }
}
