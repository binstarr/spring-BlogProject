package com.tencoding.blog.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 200)
	private String content;
	
	@CreationTimestamp
	private Timestamp createDate;
	
	// board 연관 관계 처리
	@ManyToOne
	@JoinColumn(name = "boardId") // 컬럼명 지정
	private Board board;
	
	// user 연관 관계 처리
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

}
