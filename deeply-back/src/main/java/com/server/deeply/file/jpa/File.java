package com.server.deeply.file.jpa;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File {

    @Id
    @GeneratedValue
    @Column(name = "file_id")
    private Long id = null;

    @Column(nullable = false)
    private String origFilename;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private String filePath;

}
