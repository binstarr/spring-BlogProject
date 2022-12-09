package com.example.demo.dto;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
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
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 150)
	private String title;
	
	@Lob // 대용량 데이터
	private String content;
	
	@ColumnDefault("0") // int다
	private int count;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId") // 컬럼명 내가 설정
	private User userId; // user의 userId를 FK 설정
	
	// 오브젝트를 다룰 때 가지고 와달라고 요청해야 함 = mappedBy
	// 연관 관계에 주인이 아니다. (select할 때 가지고 와야 하는 데이터다.)
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createdDate;

}
