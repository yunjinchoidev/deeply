//package com.server.deeply.order.jpa;
//
//import com.server.deeply.common.BaseEntity;
//import com.server.deeply.user.jpa.User;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.experimental.SuperBuilder;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import java.time.LocalDateTime;
//
//import com.server.deeply.common.BaseEntity;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.experimental.SuperBuilder;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//
//
//@Getter
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@SuperBuilder
//public class Order extends BaseEntity {
//
//    @Id
//    @GeneratedValue
//    @Column(name = "order_id")
//    private Long id; // 주문번호
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private User user;
//
//    private String noticeType; // 공지사항 유형
//    private String title; // 제목
//    private String contents; // 내용
//    private String writer; // 글쓴이
//    private String noticeYn; // 공지 여부
//    private String commentYn; // 댓글 가능 며부
//    private String views; // 조회수
//    private String goodCnt; // 좋아요 수
//    private String badCnt; // 싫어요 수
//
//    private Integer createId = null;
//	protected LocalDateTime createDt = null;
//    private Integer updateId = null;
//	protected LocalDateTime updateDt = null;
//
//}
