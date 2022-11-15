package com.server.deeply.file.dto;

import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileRequestDto {

    private Long id;
    private String origFilename;
    private String filename;
    private String filePath;

}
