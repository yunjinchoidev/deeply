package com.server.deeply.code.repository;

import com.server.deeply.code.dto.CodeRequestDto;
import com.server.deeply.code.dto.CodeResponseDto;
import com.server.deeply.code.jpa.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CodeRepositoryImpl implements CodeRepositoryCustom {
    @Override
    public CodeResponseDto saveCode(CodeRequestDto codeRequestDto) {

        return null;
    }
}
