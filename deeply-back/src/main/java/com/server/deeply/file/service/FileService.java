package com.server.deeply.file.service;

import com.server.deeply.file.dto.FileRequestDto;
import com.server.deeply.file.dto.FileResponseDto;
import com.server.deeply.file.jpa.File;
import com.server.deeply.file.mapper.FileMapper;
import com.server.deeply.file.repositorty.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    @Transactional
    public Long saveFile(FileRequestDto fileRequestDto) {
        File file = FileMapper.INSTANCE.toEntity(fileRequestDto);
        Long id = fileRepository.save(file).getId();
        return id;
    }

    @Transactional
    public FileResponseDto getFile(Long id) {
        File file = fileRepository.findById(id).get();
        FileResponseDto result = FileMapper.INSTANCE.toDto(file);

        return result;
    }

}
