package com.server.deeply.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
public class BaseEntity {

	@Column(name = "create_id", columnDefinition = "bigint null comment '등록자'")
	@Builder.Default
    private Integer createId = null;

	@Column(name = "create_dt", columnDefinition = "datetime default current_timestamp comment '등록일'",  updatable=false)
	@Builder.Default
	protected LocalDateTime createDt = null;

    @Column(name = "update_id", columnDefinition = "bigint null comment '수정자'")
	@Builder.Default
    private Integer updateId = null;

	@Column(name = "update_dt", columnDefinition = "datetime default null on update current_timestamp comment '수정일'",  insertable = false)
	@Builder.Default
	protected LocalDateTime updateDt = null;

}
