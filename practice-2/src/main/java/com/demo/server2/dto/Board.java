package com.demo.server2.dto;

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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 150)
	private String title;

	@Lob
	private String content;

	@ColumnDefault("0")
	private int count;

	// 테이블을 생성하는 것이 아니라, 오브젝트를 다룰 때 가지고 오도록 요청 (mappedBy)
	// Board <---> Reply 관계
	// 연관관계의 주인이 아니다. (select 할 때 가지고 와야하는 데이터이다)
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
	private List<Reply> reply;

	@CreationTimestamp
	private Timestamp createdDate;
}
