package com.example.demo.dto;

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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Reply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 200)
	private String content;
	
	@CreationTimestamp
	private Timestamp timestamp;
	
	// Board 연관 관계 처리
	@ManyToOne
	@JoinColumn(name = "boardId") // 컬럼명 지정
	private Board board;
	
	// User 연관 관계 처리
	@ManyToOne
	@JoinColumn(name = "userId") // 컬럼명 지정
	private User user;
	

}
