package com.server.deeply.code.repository;

import com.server.deeply.code.dto.CodeRequestDto;
import com.server.deeply.code.dto.CodeResponseDto;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepositoryCustom {
    CodeResponseDto saveCode(CodeRequestDto codeRequestDto);

}
