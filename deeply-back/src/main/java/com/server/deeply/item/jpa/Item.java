package com.server.deeply.item.jpa;

import com.server.deeply.common.BaseEntity;
import com.server.deeply.user.jpa.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Item extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private Long cnt;
    private String itemType;

    private Integer createId = null;
	protected LocalDateTime createDt = null;
    private Integer updateId = null;
	protected LocalDateTime updateDt = null;

}
