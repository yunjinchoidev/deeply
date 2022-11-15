package com.server.deeply.code.controller;

import com.server.deeply.code.dto.CodeRequestDto;
import com.server.deeply.code.dto.CodeResponseDto;
import com.server.deeply.code.service.CodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CodeController {

    private final CodeService codeService;

    @PostMapping("/code")
    public ResponseEntity saveCode(@RequestBody CodeRequestDto codeRequest){
        Long codeId = codeService.saveCode(codeRequest);
        HashMap<String, Long> result = new HashMap<>();
        result.put("codeId",codeId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


}
