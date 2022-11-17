package com.server.deeply.file.service;

import com.server.deeply.config.MD5Generator;
import com.server.deeply.file.dto.FileRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class UploadService {

    private final FileService fileService;

    public HashMap<String, Long> upload(MultipartFile files) {
        HashMap<String, Long> result = new HashMap<>();
        try {
            String origFilename = files.getOriginalFilename();
            String filename = new MD5Generator(origFilename).toString();
            /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
//            String savePath = System.getProperty("user.dir") + File.separator + "files";
            String savePath = "src/main/resources/static/imgs";
            /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
            if (!new File(savePath).exists()) {
                try {
                    new File(savePath).mkdir();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
            String filePath = savePath + File.separator + origFilename;
//            files.transferTo(new File(filePath));
            File file = new File(filePath);
            try {
            InputStream fileStream = files.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, file);
        } catch (IOException e) {
            FileUtils.deleteQuietly(file);
            e.printStackTrace();
        }
            FileRequestDto fileRequestDto = new FileRequestDto();
            fileRequestDto.setOrigFilename(origFilename);
            fileRequestDto.setFilename(filename);
            fileRequestDto.setFilePath(filePath);
            log.info("fileService -> saveFile");
            Long fileId = fileService.saveFile(fileRequestDto);
            result.put("fileId", fileId);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }
}
