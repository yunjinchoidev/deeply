package com.server.deeply.file.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileResponseDto {

    private Long id;
    private String origFilename;
    private String filename;
    private String filePath;

}
