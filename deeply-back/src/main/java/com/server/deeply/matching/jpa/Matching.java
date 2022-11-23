package com.server.deeply.matching.jpa;

import com.server.deeply.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import javax.persistence.*;
import java.time.LocalDateTime;



@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Matching extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "matching_id")
    private Long id;

    private String name;
    private Long cnt;
    private String itemType;

    private Integer createId = null;
	protected LocalDateTime createDt = null;
    private Integer updateId = null;
	protected LocalDateTime updateDt = null;

}
