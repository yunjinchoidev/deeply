package com.server.deeply.board.controller;

import com.server.deeply.board.dto.BoardRequestDto;
import com.server.deeply.board.dto.BoardResponseDto;
import com.server.deeply.board.service.BoardService;
import com.server.deeply.config.MD5Generator;
import com.server.deeply.file.dto.FileRequestDto;
import com.server.deeply.file.service.FileService;
import com.server.deeply.profile.dto.ProfileRequestDto;
import com.server.deeply.profile.dto.ProfileResponseDto;
import com.server.deeply.profile.dto.ProfileSearchRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.HashMap;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {

    private final FileService fileService;
    private final BoardService boardService;

    /**
     * 게시판 저장
     * @param files
     * @param boardRequestDto
     * @return
     */
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
            log.info("boardService -> saveBoard");
            Long boardId = boardService.saveBoard(boardRequestDto);
            result.put("boardId", boardId);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }


      /**
     * 게시판 정보 리스트 조회
     */
    @PostMapping("/board/list")
    public ResponseEntity getboardList(@RequestBody BoardRequestDto param) {
        log.info("boardService->findAll");
        Page<BoardResponseDto> result = boardService.searchAll(param);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

   /**
     * 게시판 단일 조회
     */
    @GetMapping("/board/{id}")
    public ResponseEntity getBoard(@PathVariable Long id) {
        log.info("boardService -> findboard");
        BoardRequestDto boardRequestDto = BoardRequestDto.builder()
                .id(id)
                .build();
        log.info("boardService->findboardById");
        BoardResponseDto result = boardService.getPost(boardRequestDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }



    /**
     * 게시판 정보 수정
     */
    @PutMapping("/board")
    public ResponseEntity updateboard(@RequestBody BoardRequestDto param) {
        log.info("boardService->saveboard");
        Long boardId = boardService.saveBoard(param);
        HashMap<String,Long> result = new HashMap<>();
        result.put("boardId", boardId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }




}

