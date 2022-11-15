package com.server.deeply.board.controller;

import com.server.deeply.board.dto.BoardRequestDto;
import com.server.deeply.board.service.BoardService;
import com.server.deeply.config.MD5Generator;
import com.server.deeply.file.dto.FileRequestDto;
import com.server.deeply.file.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.HashMap;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BoardController {

    private final FileService fileService;
    private final BoardService boardService;

    @PostMapping("/board")
    public ResponseEntity write(@RequestParam("file") MultipartFile files,
                                 BoardRequestDto boardRequestDto) {
        HashMap<String, Long> result = new HashMap<>();
        try {
            String origFilename = files.getOriginalFilename();
            String filename = new MD5Generator(origFilename).toString();
            /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
            String savePath = System.getProperty("user.dir") + File.separator+"files";
            /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
            if (!new File(savePath).exists()) {
                try {
                    new File(savePath).mkdir();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
            String filePath = savePath + File.separator + filename;
            files.transferTo(new File(filePath));

            FileRequestDto fileRequestDto = new FileRequestDto();
            fileRequestDto.setOrigFilename(origFilename);
            fileRequestDto.setFilename(filename);
            fileRequestDto.setFilePath(filePath);
            log.info("fileService -> saveFile");
            Long fileId = fileService.saveFile(fileRequestDto);
            result.put("fileId", fileId);
            boardRequestDto.setFileId(fileId);
            log.info("boardService -> savePost");
            Long boardId = boardService.savePost(boardRequestDto);
            result.put("boardId", boardId);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }
}

