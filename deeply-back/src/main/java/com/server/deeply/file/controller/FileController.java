package com.server.deeply.file.controller;

import com.server.deeply.file.dto.FileResponseDto;
import com.server.deeply.file.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@Slf4j
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    /**
     * 파일 다운로드
     *
     * @param fileId
     * @return
     * @throws IOException
     */
    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> fileDownload(@PathVariable("fileId") Long fileId) throws IOException {
        FileResponseDto fileResponseDto = fileService.getFile(fileId);
        Path path = Paths.get(fileResponseDto.getFilePath());
        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileResponseDto.getOrigFilename() + "\"")
                .body(resource);
    }


    /**
     * 파일 display
     *
     * @param fileId
     * @return
     * @throws IOException
     */
    @GetMapping("/display/{fileId}")
    public ResponseEntity<Resource> fileDisplay(@PathVariable("fileId") Long fileId) throws IOException {
        String path = "file:///Users/yunjinchoi/a07_project/deeply/deeply-back/files/";
        FileResponseDto fileResponseDto = fileService.getFile(fileId);
        Path paths = Paths.get(fileResponseDto.getFilePath());


        HttpHeaders httpHeaders = new HttpHeaders();
        Path filePath = null;
        Resource resource = new FileSystemResource(paths);
        try {
            filePath = Paths.get(fileResponseDto.getFilePath());
            httpHeaders.add("Content-Type", Files.probeContentType(filePath));
        } catch (IOException e){
            e.printStackTrace();
        }
        return new ResponseEntity<Resource>(resource, httpHeaders, HttpStatus.OK);


    }
}
